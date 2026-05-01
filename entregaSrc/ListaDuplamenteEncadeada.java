public class ListaDuplamenteEncadeada<T> {
    private NoDuplo<T> head;

    public void inserirInicio(T dado) {
        var novoNo = new NoDuplo<>(dado);
        var headAntigo = this.head;

        if(headAntigo != null) {
           atualizarRefsInsercao(headAntigo, novoNo);
        }

        this.head = novoNo;
    }

    public void inserirFinal(T dado) {
        var novoNo = new NoDuplo<>(dado);
        if(head == null) {
            inserirInicio(dado);
            return;
        }

        var noAtual = this.head;

        while (noAtual != null) {
            if(noAtual.getProximo() == null) {
                atualizarRefsInsercao(novoNo, noAtual);
                return;
            }

            noAtual = noAtual.getProximo();
        }
    }

    private void atualizarRefsInsercao(NoDuplo<T> novoNo, NoDuplo<T> noAnterior) {
        noAnterior.setProximo(novoNo);
        novoNo.setAnterior(noAnterior);
    }

    public void deletar(int posicao) {
        var noAtual = this.head;

        for(int i = 0; noAtual != null; i++) {
            if(i == posicao) {
                var anterior = noAtual.getAnterior();
                var proximo = noAtual.getProximo();

                if(noAtual == this.head) { // Se for o inicio
                    this.head = proximo;
                    return;
                }

                anterior.setProximo(proximo);

                if(proximo != null) { // Se for o final
                    proximo.setAnterior(anterior);
                }

                return;
            }

            noAtual = noAtual.getProximo();
        }
    }

    public void mostrarLista() {
        var noAtual = this.head;
        while (noAtual != null) {
            System.out.printf("[%s]", noAtual.getDado());

            var hasProximo = noAtual.getProximo() != null;

            System.out.print(hasProximo ? " <-> " : " -> ");

            noAtual = noAtual.getProximo();
        }

        System.out.println("null");
    }
}
