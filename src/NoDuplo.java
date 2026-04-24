public class NoDuplo<T> {
    private T dado;
    private NoDuplo<T> proximo;
    private NoDuplo<T> anterior;

    public NoDuplo(T dado) {
        this.dado = dado;
        this.proximo = null;
        this.anterior = null;
    }

    public T getDado()                    { return dado; }
    public void setDado(T dado)           { this.dado = dado; }
    public NoDuplo<T> getProximo()        { return proximo; }
    public void setProximo(NoDuplo<T> p)  { this.proximo = p; }
    public NoDuplo<T> getAnterior()       { return anterior; }
    public void setAnterior(NoDuplo<T> a) { this.anterior = a; }
}
