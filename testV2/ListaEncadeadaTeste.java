import lib.PrintMock;
import lib.Teste;

public class ListaEncadeadaTeste {
    @Teste("Inserir e mostrar normal")
    public boolean inserir(PrintMock printMock) {
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

    @Teste("Inserir e mostrar recursivo")
    public boolean inserirRecursivo(PrintMock printMock) {
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
