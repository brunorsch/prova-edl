import lib.PrintMock;
import lib.Teste;

public class ListaDuplamenteEncadeadaTeste {
    @Teste // Tb funciona sem nome
    public boolean deletar(PrintMock printMock) {
        // Arrange
        var listaDupla = new ListaDuplamenteEncadeada<String>();
        listaDupla.inserirInicio("Inicio");
        listaDupla.inserirFinal("Segundo");
        listaDupla.inserirFinal("Ultimo");

        // Act
        listaDupla.deletar(1);

        // Assert
        listaDupla.mostrarLista();
        return printMock.getPrintCapturado()
            .equals("[Inicio] <-> [Ultimo] -> null");
    }

    @Teste
    public boolean inserirFinal(PrintMock printMock) {
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

    @Teste
    public boolean inserirInicio(PrintMock printMock) {
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
