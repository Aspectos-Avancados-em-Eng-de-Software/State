package padroescomportamentais.state;

public class Pedido {
    private String id;
    private PedidoEstado estado;

    public Pedido(String id) {
        this.id = id;
        // estado inicial: Novo
        this.estado = PedidoEstadoNovo.getInstance();
    }

    public String getId() {
        return id;
    }

    public PedidoEstado getEstado() {
        return estado;
    }

    public void setEstado(PedidoEstado novoEstado) {
        this.estado = novoEstado;
    }

    // métodos de transiao que delegam para o objeto de estado atual
    public boolean pagar() {
        return estado.pagar(this);
    }

    public boolean preparar() {
        return estado.preparar(this);
    }

    public boolean enviar() {
        return estado.enviar(this);
    }

    public boolean entregar() {
        return estado.entregar(this);
    }

    public boolean cancelar() {
        return estado.cancelar(this);
    }

    public String getStatus() {
        return estado.getStatus();
    }
}
