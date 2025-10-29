package padroescomportamentais.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PedidoTest {

    private Pedido pedido;

    @BeforeEach
    public void setUp() {
        pedido = new Pedido("PED001");
    }

    // estado novo
    @Test
    public void testPedidoNovoStatus() {
        assertEquals("Novo", pedido.getStatus());
    }

    @Test
    public void testPedidoNovoPagar() {
        pedido.setEstado(PedidoEstadoNovo.getInstance());
        assertTrue(pedido.pagar());
        assertEquals("Pago", pedido.getStatus());
    }

    @Test
    public void testPedidoNovoCancelar() {
        pedido.setEstado(PedidoEstadoNovo.getInstance());
        assertTrue(pedido.cancelar());
        assertEquals("Cancelado", pedido.getStatus());
    }

    // estado pago
    @Test
    public void testPedidoPagoStatus() {
        pedido.setEstado(PedidoEstadoPago.getInstance());
        assertEquals("Pago", pedido.getStatus());
    }

    @Test
    public void testPedidoPagoPreparar() {
        pedido.setEstado(PedidoEstadoPago.getInstance());
        assertTrue(pedido.preparar());
        assertEquals("Em Preparação", pedido.getStatus());
    }

    @Test
    public void testPedidoPagoCancelar() {
        pedido.setEstado(PedidoEstadoPago.getInstance());
        assertTrue(pedido.cancelar());
        assertEquals("Cancelado", pedido.getStatus());
    }

    // estado em preparacao
    @Test
    public void testPedidoEmPreparacaoStatus() {
        pedido.setEstado(PedidoEstadoEmPreparacao.getInstance());
        assertEquals("Em Preparação", pedido.getStatus());
    }

    @Test
    public void testPedidoEmPreparacaoEnviar() {
        pedido.setEstado(PedidoEstadoEmPreparacao.getInstance());
        assertTrue(pedido.enviar());
        assertEquals("Enviado", pedido.getStatus());
    }

    @Test
    public void testPedidoEmPreparacaoCancelar() {
        pedido.setEstado(PedidoEstadoEmPreparacao.getInstance());
        assertTrue(pedido.cancelar());
        assertEquals("Cancelado", pedido.getStatus());
    }

    // estado enviado
    @Test
    public void testPedidoEnviadoStatus() {
        pedido.setEstado(PedidoEstadoEnviado.getInstance());
        assertEquals("Enviado", pedido.getStatus());
    }

    @Test
    public void testPedidoEnviadoEntregar() {
        pedido.setEstado(PedidoEstadoEnviado.getInstance());
        assertTrue(pedido.entregar());
        assertEquals("Entregue", pedido.getStatus());
    }

    @Test
    public void testPedidoEnviadoCancelar() {
        pedido.setEstado(PedidoEstadoEnviado.getInstance());
        assertTrue(pedido.cancelar());
        assertEquals("Cancelado", pedido.getStatus());
    }

    // estado entregue
    @Test
    public void testPedidoEntregueStatus() {
        pedido.setEstado(PedidoEstadoEntregue.getInstance());
        assertEquals("Entregue", pedido.getStatus());
    }

    @Test
    public void testPedidoEntregueNaoPermiteTransicoes() {
        pedido.setEstado(PedidoEstadoEntregue.getInstance());
        assertFalse(pedido.pagar());
        assertFalse(pedido.preparar());
        assertFalse(pedido.enviar());
        assertFalse(pedido.cancelar());
    }

    // estado cancelado
    @Test
    public void testPedidoCanceladoStatus() {
        pedido.setEstado(PedidoEstadoCancelado.getInstance());
        assertEquals("Cancelado", pedido.getStatus());
    }

    @Test
    public void testPedidoCanceladoNaoPermiteTransicoes() {
        pedido.setEstado(PedidoEstadoCancelado.getInstance());
        assertFalse(pedido.pagar());
        assertFalse(pedido.preparar());
        assertFalse(pedido.enviar());
        assertFalse(pedido.entregar());
    }

    // fluxo completo
    @Test
    public void testFluxoCompletoDoEstado() {
        // começa em novo
        assertEquals("Novo", pedido.getStatus());

        // transição para pago
        assertTrue(pedido.pagar());
        assertEquals("Pago", pedido.getStatus());

        // transição para em preparação
        assertTrue(pedido.preparar());
        assertEquals("Em Preparação", pedido.getStatus());

        // transição para enviado
        assertTrue(pedido.enviar());
        assertEquals("Enviado", pedido.getStatus());

        // transição para entregue
        assertTrue(pedido.entregar());
        assertEquals("Entregue", pedido.getStatus());

        // verifica que não permite mais transições
        assertFalse(pedido.pagar());
    }

    @Test
    public void testCancelamentoEmDiferentesEstados() {
        // Cancela a partir do estado Novo
        pedido.setEstado(PedidoEstadoNovo.getInstance());
        assertTrue(pedido.cancelar());
        assertEquals("Cancelado", pedido.getStatus());

        // Cria novo pedido e cancela a partir do estado Pago
        Pedido pedido2 = new Pedido("PED002");
        pedido2.setEstado(PedidoEstadoPago.getInstance());
        assertTrue(pedido2.cancelar());
        assertEquals("Cancelado", pedido2.getStatus());

        // Cria novo pedido e cancela a partir do estado Em Preparação
        Pedido pedido3 = new Pedido("PED003");
        pedido3.setEstado(PedidoEstadoEmPreparacao.getInstance());
        assertTrue(pedido3.cancelar());
        assertEquals("Cancelado", pedido3.getStatus());
    }
}
