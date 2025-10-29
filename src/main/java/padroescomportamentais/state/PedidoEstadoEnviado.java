package padroescomportamentais.state;

public class PedidoEstadoEnviado extends PedidoEstado {

    private static PedidoEstadoEnviado instance = new PedidoEstadoEnviado();

    private PedidoEstadoEnviado() {
    }

    public static PedidoEstadoEnviado getInstance() {
        return instance;
    }

    @Override
    public String getStatus() {
        return "Enviado";
    }

    @Override
    public boolean entregar(Pedido pedido) {
        pedido.setEstado(PedidoEstadoEntregue.getInstance());
        return true;
    }

    @Override
    public boolean cancelar(Pedido pedido) {
        pedido.setEstado(PedidoEstadoCancelado.getInstance());
        return true;
    }
}
