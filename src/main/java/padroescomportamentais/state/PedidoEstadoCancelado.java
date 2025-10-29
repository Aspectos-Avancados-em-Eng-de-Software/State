package padroescomportamentais.state;

public class PedidoEstadoCancelado extends PedidoEstado {

    private static PedidoEstadoCancelado instance = new PedidoEstadoCancelado();

    private PedidoEstadoCancelado() {
    }

    public static PedidoEstadoCancelado getInstance() {
        return instance;
    }

    @Override
    public String getStatus() {
        return "Cancelado";
    }

    // estado final: nao permite transições
}