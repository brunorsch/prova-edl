public class ListaEncadeada<T> implements Lista<T> {
    private No<T> head;

    @Override
    public void inserir(T dado) {
        var novoNo = new No<>(dado);
        if (head == null) {
           this.head = novoNo;
            return;
        }

        var noAtual = this.head;

        while(noAtual != null) {
            if(noAtual.getProximo() == null) {
                noAtual.setProximo(novoNo);
                break;
            }

            noAtual = noAtual.getProximo();
        }
    }

    @Override
    public void inserirRecursivo(T dado) {
        var novoNo = new No<>(dado);

        if (head == null) {
            this.head = novoNo;
            return;
        }

        inserirRecursivo(this.head, novoNo);
    }

    private void inserirRecursivo(No<T> noAtual, No<T> novoNo) {
        if(noAtual == null) return;

        if(noAtual.getProximo() == null) {
            noAtual.setProximo(novoNo);
            return;
        }

        inserirRecursivo(noAtual.getProximo(), novoNo);
    }

    @Override
    public void mostrarLista() {
        if (head != null) {
            var noAtual = this.head;

            while(noAtual != null) {
                System.out.printf("[%s] -> ", noAtual.getDado());

                noAtual = noAtual.getProximo();
            }
        }

        System.out.println("null");
    }

    @Override
    public void mostrarListaRecursivo() {
        if(this.head != null) {
            mostrarNo(this.head);
        }

        System.out.println("null");
    }

    private void mostrarNo(No<T> atual) {
        if(atual == null) return;

        System.out.printf("[%s] -> ", atual.getDado());

        mostrarNo(atual.getProximo());
    }
}
