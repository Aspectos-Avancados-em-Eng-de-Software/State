package padroescomportamentais.state;

public class PedidoEstadoEmPreparacao extends PedidoEstado {

    private static PedidoEstadoEmPreparacao instance = new PedidoEstadoEmPreparacao();

    private PedidoEstadoEmPreparacao() {
    }

    public static PedidoEstadoEmPreparacao getInstance() {
        return instance;
    }

    @Override
    public String getStatus() {
        return "Em Preparação";
    }

    @Override
    public boolean enviar(Pedido pedido) {
        pedido.setEstado(PedidoEstadoEnviado.getInstance());
        return true;
    }

    @Override
    public boolean cancelar(Pedido pedido) {
        pedido.setEstado(PedidoEstadoCancelado.getInstance());
        return true;
    }
}
