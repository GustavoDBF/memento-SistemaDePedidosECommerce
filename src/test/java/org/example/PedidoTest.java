package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    @Test
    void deveArmazenarEstados() {
        Pedido pedido = new Pedido();
        pedido.setEstado(PedidoEstadoNovo.getInstance());
        pedido.setEstado(PedidoEstadoPendentePagamento.getInstance());
        assertEquals(2, pedido.getEstados().size());
    }

    @Test
    void deveRetornarEstadoInicial() {
        Pedido pedido = new Pedido();
        pedido.setEstado(PedidoEstadoNovo.getInstance());
        pedido.setEstado(PedidoEstadoPago.getInstance());
        pedido.restauraEstado(0);
        assertEquals(PedidoEstadoNovo.getInstance(), pedido.getEstado());
    }

    @Test
    void deveRetornarEstadoAnterior() {
        Pedido pedido = new Pedido();
        pedido.setEstado(PedidoEstadoNovo.getInstance());
        pedido.setEstado(PedidoEstadoPendentePagamento.getInstance());
        pedido.setEstado(PedidoEstadoCancelado.getInstance());
        pedido.setEstado(PedidoEstadoPendentePagamento.getInstance());
        pedido.restauraEstado(2);
        assertEquals(PedidoEstadoCancelado.getInstance(), pedido.getEstado());
    }

    @Test
    void deveRetornarExcecaoIndiceInvalido() {
        try {
            Pedido pedido = new Pedido();
            pedido.restauraEstado(0);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Índice inválido", e.getMessage());
        }
    }
}