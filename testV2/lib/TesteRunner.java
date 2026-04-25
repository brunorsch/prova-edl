package lib;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public final class TesteRunner {
    private final Class<?> testeClass;
    private final List<Method> metodosTeste;
    private Consumer<TesteInfo> onTesteIniciando = (_) -> {};
    private BiConsumer<TesteInfo, Boolean> onTesteFinalizado = (_, _) -> {};

    public TesteRunner(Class<?> testeClass) {
        this.testeClass = testeClass;

        if(testeClass.getDeclaredConstructors().length != 1) {
            throw new IllegalArgumentException("A classe de teste " + testeClass.getSimpleName() + " deve ter apenas um construtor padrão sem parâmetros.");
        }

        this.metodosTeste = new ArrayList<>();
        carregarMetodosTeste();
    }

    public TesteRunner onTesteIniciando(Consumer<TesteInfo> onTesteIniciando) {
        this.onTesteIniciando = onTesteIniciando;
        return this;
    }

    public TesteRunner onTesteFinalizado(BiConsumer<TesteInfo, Boolean> onTesteFinalizado) {
        this.onTesteFinalizado = onTesteFinalizado;
        return this;
    }

    public void executarTodos() {
        for (Method metodo : metodosTeste) {
            try {
                var testeInstance = testeClass.getDeclaredConstructors()[0].newInstance();
                var testeInfo = new TesteInfo(nomeTestePorMethod(metodo));

                onTesteIniciando.accept(testeInfo);

                var deveInjetarMock = metodo.getParameterCount() == 1
                    && metodo.getParameterTypes()[0] == PrintMock.class;
                boolean result = false;

                if(deveInjetarMock) {
                    var printMock = new PrintMock();
                    printMock.mockarSaida();
                    metodo.setAccessible(true);
                    result = (boolean) metodo.invoke(testeInstance, printMock);
                    printMock.restaurarSaidaOriginal();
                } else {
                    result = (boolean) metodo.invoke(testeInstance);
                }

                onTesteFinalizado.accept(testeInfo, result);
            } catch (Exception e) {
                IO.println("Erro ao executar teste: " + nomeTestePorMethod(metodo) + ": " + e.getLocalizedMessage());
                e.printStackTrace();
            }
        }
    }

    public int total() {
        return metodosTeste.size();
    }

    private void carregarMetodosTeste() {
        Arrays.stream(testeClass.getDeclaredMethods())
            .filter(m -> m.isAnnotationPresent(Teste.class))
            .filter(m -> {
                if(m.getParameterCount() <= 1 && (m.getParameterCount() == 1 && m.getParameterTypes()[0] == PrintMock.class)) {
                    return true;
                } else {
                    IO.println("AVISO: Teste " + nomeTestePorMethod(m) + " ignorado, pois o método de teste deve ter no máximo um parâmetro do tipo PrintMock.");
                    return false;
                }
            })
            .filter(m -> {
                if(m.getReturnType() == boolean.class) {
                    return true;
                } else {
                    IO.println("AVISO: Teste " + nomeTestePorMethod(m) + " ignorado, pois o método de teste deve retornar boolean.");
                    return false;
                }
            })
            .forEach(metodosTeste::add);
    }

    private String nomeTestePorMethod(Method method) {
        return method.getAnnotation(Teste.class).value().isEmpty() ?
            method.getName() : method.getAnnotation(Teste.class).value();
    }

    public record TesteInfo(String nome) {}
}
