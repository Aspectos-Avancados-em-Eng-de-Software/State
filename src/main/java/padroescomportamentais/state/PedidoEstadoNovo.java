package padroescomportamentais.state;

public class PedidoEstadoNovo extends PedidoEstado {

    private static PedidoEstadoNovo instance = new PedidoEstadoNovo();

    private PedidoEstadoNovo() {
    }

    public static PedidoEstadoNovo getInstance() {
        return instance;
    }

    @Override
    public String getStatus() {
        return "Novo";
    }

    @Override
    public boolean pagar(Pedido pedido) {
        pedido.setEstado(PedidoEstadoPago.getInstance());
        return true;
    }

    @Override
    public boolean cancelar(Pedido pedido) {
        pedido.setEstado(PedidoEstadoCancelado.getInstance());
        return true;
    }
}
