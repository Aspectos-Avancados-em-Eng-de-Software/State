package padroescomportamentais.state;

public class PedidoEstadoPago extends PedidoEstado {

    private static PedidoEstadoPago instance = new PedidoEstadoPago();

    private PedidoEstadoPago() {
    }

    public static PedidoEstadoPago getInstance() {
        return instance;
    }

    @Override
    public String getStatus() {
        return "Pago";
    }

    @Override
    public boolean preparar(Pedido pedido) {
        pedido.setEstado(PedidoEstadoEmPreparacao.getInstance());
        return true;
    }

    @Override
    public boolean cancelar(Pedido pedido) {
        pedido.setEstado(PedidoEstadoCancelado.getInstance());
        return true;
    }
}