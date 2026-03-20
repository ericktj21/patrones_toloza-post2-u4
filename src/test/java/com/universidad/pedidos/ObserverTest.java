package com.universidad.pedidos;

import com.universidad.pedidos.modelo.Pedido;
import com.universidad.pedidos.observer.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ObserverTest {

    @Autowired
    private GestorPedidosService gestorPedidosService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Test
    void testConfirmarPedidoPublicaEvento() {
        Pedido pedido = new Pedido("P001", 100000.0);
        assertDoesNotThrow(() -> gestorPedidosService.confirmarPedido(pedido));
        assertEquals("CONFIRMADO", pedido.getEstado());
    }

    @Test
    void testCancelarPedidoPublicaEvento() {
        Pedido pedido = new Pedido("P002", 100000.0);
        assertDoesNotThrow(() -> gestorPedidosService.cancelarPedido(pedido));
        assertEquals("CANCELADO", pedido.getEstado());
    }

    @Test
    void testSuscriptoresReaccionanAEventosConfirmacion() {
        Pedido pedido = new Pedido("P003", 100000.0);
        // Los suscriptores deben reaccionar sin excepción
        assertDoesNotThrow(() -> gestorPedidosService.confirmarPedido(pedido));
    }

    @Test
    void testSuscriptoresReaccionanAEventosCancelacion() {
        Pedido pedido = new Pedido("P004", 100000.0);
        // Los suscriptores deben reaccionar sin excepción
        assertDoesNotThrow(() -> gestorPedidosService.cancelarPedido(pedido));
    }
}
