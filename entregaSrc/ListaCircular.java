public class ListaCircular<T> {
    private NoDuplo<T> head;

    public void inserir(T dado) {
        var novoNo = new NoDuplo<>(dado);
        if(this.head == null) {
            novoNo.setProximo(novoNo);
            this.head = novoNo;
            return;
        }

        var noAtual = this.head.getProximo();

        if(noAtual == this.head) { // Se a lista tiver um elemento
            noAtual.setProximo(novoNo);
            novoNo.setAnterior(noAtual);
            novoNo.setProximo(this.head);
            this.head.setAnterior(novoNo);
            return;
        }

        while(noAtual != this.head) {
            if(noAtual.getProximo() == this.head) {
                noAtual.setProximo(novoNo);

                novoNo.setAnterior(noAtual);
                novoNo.setProximo(this.head);
                this.head.setAnterior(novoNo);
                return;
            }


            noAtual = noAtual.getProximo();
        }
    }

    public void mostrarLista() {
        var noAtual = this.head;
        var segundoNo = noAtual.getProximo();
        var isPrimeiraIteracao = true;

        while(isPrimeiraIteracao || noAtual.getProximo() != segundoNo) {
            System.out.printf("[%s] -> ", noAtual.getDado());
            noAtual = noAtual.getProximo();

            if(isPrimeiraIteracao) isPrimeiraIteracao = false;
        }

        System.out.println("(volta ao inicio)");
    }
}
