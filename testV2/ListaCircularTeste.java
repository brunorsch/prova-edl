import lib.PrintMock;
import lib.Teste;

public class ListaCircularTeste {
    @Teste("Inserir Unico")
    boolean unicoItem(PrintMock printMock) {
        // Arrange
        var listaCircular = new ListaCircular<String>();

        // Act
        listaCircular.inserir("Inicio");

        // Assert
        listaCircular.mostrarLista();
        return printMock.getPrintCapturado()
            .equals("[Inicio] -> (volta ao inicio)");
    }

    @Teste("Inserir Varios")
    boolean variosItens(PrintMock printMock) {
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
