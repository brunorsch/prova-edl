import lib.TesteRunner;

void main() {
    executar(ListaEncadeadaTeste.class);
    executar(ListaDuplamenteEncadeadaTeste.class);
    executar(ListaCircularTeste.class);
}

public static void executar(Class<?> testeClass) {
    var testeRunner = new TesteRunner(testeClass);

    final var total = testeRunner.total();
    AtomicInteger testesExecutados = new AtomicInteger(1);

    IO.println("Iniciando execução de testes: " + testeClass.getSimpleName());
    System.out.printf("Total de testes da classe: %d%n", total);

    testeRunner
        .onTesteIniciando(teste -> System.out.printf("➡️ Iniciando teste (%d/%d): %s%n",
            testesExecutados.getAndIncrement(), total, teste.nome()))
        .onTesteFinalizado((teste, resultado) -> System.out.printf("💡 Resultado teste %s: %s%n",
            teste.nome(), resultado ? "✅ PASSOU" : "❌ FALHOU"))
        .executarTodos();

    IO.println();
}
