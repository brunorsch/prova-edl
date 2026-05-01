import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

private static final List<Teste> testes = List.of(
        new InserirListaEncadeadaTeste(),
        new InserirListaEncadeadaRecursivaTeste(),
        new InserirInicioListaDuplamenteEncadeadaTeste(),
        new InserirFinalListaDuplamenteEncadeadaTeste(),
        new ListaCircularUnicoItemTeste(),
        new ListaCircularVariosItensTeste(),
        new DeletarListaDuplamenteEncadeadaTeste()
);


void main() {
    testes.forEach(t -> {
        try {
            var printMock = new PrintMock();
            printMock.mockarSaida();
            var testePassou = t.execute(printMock);
            printMock.restaurarSaidaOriginal();

            if (testePassou) {
                IO.println("Teste passou: " + t.getClass().getSimpleName());
            } else {
                IO.println("Teste não passou: " + t.getClass().getSimpleName());
            }
        } catch (Exception e) {
            IO.println("Erro no teste: " + t.getClass().getSimpleName() + ": " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    });
}

private static class InserirListaEncadeadaTeste implements Teste {
    @Override
    public boolean execute(PrintMock printMock) {
        //Arrange
        var listaEncadeada = new ListaEncadeada<String>();
        listaEncadeada.inserir("Inicio");

        // Act
        listaEncadeada.inserir("Segundo");

        // Assert
        listaEncadeada.mostrarLista();
        return printMock.getPrintCapturado()
            .equals("[Inicio] -> [Segundo] -> null");
    }
}

private static class InserirListaEncadeadaRecursivaTeste implements Teste {
    @Override
    public boolean execute(PrintMock printMock) {
        //Arrange
        var listaEncadeada = new ListaEncadeada<String>();
        listaEncadeada.inserirRecursivo("Inicio");

        // Act
        listaEncadeada.inserirRecursivo("Segundo");

        // Assert
        listaEncadeada.mostrarListaRecursivo();
        return printMock.getPrintCapturado()
            .equals("[Inicio] -> [Segundo] -> null");
    }
}

private static class InserirInicioListaDuplamenteEncadeadaTeste implements Teste {
    @Override
    public boolean execute(PrintMock printMock) {
        // Arrange
        var listaDupla = new ListaDuplamenteEncadeada<String>();
        listaDupla.inserirInicio("Inicio");

        // Act
        listaDupla.inserirInicio("NovoInicio");

        // Assert
        listaDupla.mostrarLista();
        return printMock.getPrintCapturado()
            .equals("[NovoInicio] <-> [Inicio] -> null");
    }
}

private static class InserirFinalListaDuplamenteEncadeadaTeste implements Teste {
    @Override
    public boolean execute(PrintMock printMock) {
        // Arrange
        var listaDupla = new ListaDuplamenteEncadeada<String>();
        listaDupla.inserirInicio("Inicio");

        // Act
        listaDupla.inserirFinal("Segundo");
        listaDupla.inserirFinal("Ultimo");

        // Assert
        listaDupla.mostrarLista();
        return printMock.getPrintCapturado()
            .equals("[Inicio] <-> [Segundo] <-> [Ultimo] -> null");
    }
}

private static class DeletarListaDuplamenteEncadeadaTeste implements Teste {
    @Override
    public boolean execute(PrintMock printMock) {
        // Arrange
        var listaDupla = new ListaDuplamenteEncadeada<String>();
        listaDupla.inserirInicio("Inicio");
        listaDupla.inserirFinal("Segundo");
        listaDupla.inserirFinal("Ultimo");

        // Act
        listaDupla.deletar(0);

        // Assert
        listaDupla.mostrarLista();
        return printMock.getPrintCapturado()
            .equals("[Segundo] <-> [Ultimo] -> null");
    }
}

private static class ListaCircularUnicoItemTeste implements Teste {
    @Override
    public boolean execute(PrintMock printMock) {
        // Arrange
        var listaCircular = new ListaCircular<String>();

        // Act
        listaCircular.inserir("Inicio");

        // Assert
        listaCircular.mostrarLista();
        return printMock.getPrintCapturado()
            .equals("[Inicio] -> (volta ao inicio)");
    }
}

private static class ListaCircularVariosItensTeste implements Teste {
    @Override
    public boolean execute(PrintMock printMock) {
        // Arrange
        var listaCircular = new ListaCircular<String>();
        listaCircular.inserir("Inicio");

        // Act
        listaCircular.inserir("Segundo");
        listaCircular.inserir("Ultimo");

        // Assert
        listaCircular.mostrarLista();
        return printMock.getPrintCapturado()
            .equals("[Inicio] -> [Segundo] -> [Ultimo] -> (volta ao inicio)");
    }
}

public static class PrintMock {
    private PrintStream original;
    private ByteArrayOutputStream streamMock;

    public PrintMock() {
        original = System.out;
        streamMock = new ByteArrayOutputStream();
    }

    public void mockarSaida() {
        System.setOut(new PrintStream(streamMock));
    }

    public String getPrintCapturado() {
        return streamMock.toString().trim();
    }

    public void restaurarSaidaOriginal() {
        System.setOut(original);
    }
}

interface Teste {
    boolean execute(PrintMock printMock);
}
