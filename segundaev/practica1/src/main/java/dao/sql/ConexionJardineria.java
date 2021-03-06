package dao.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class ConexionJardineria implements AutoCloseable {
    Dotenv dotenv;

    private Connection conn;

    private PedidosSqlDao pedidosDao;
    private ClientesSqlDao clientesDao;
    private ProductosSqlDao productosDao;

    private void initDaos() {
        clientesDao = new ClientesSqlDao(conn);
        pedidosDao = new PedidosSqlDao(conn);
        productosDao = new ProductosSqlDao(conn);
    }

    public boolean login() {
        dotenv = Dotenv.load();
        String url = "jdbc:" + dotenv.get("DB_TYPE") + "://" + dotenv.get("DB_SOCKET") + "/" + dotenv.get("SCHEMA");
        try {
            System.out.println("Me voy a conectar a: " + url);
            conn = DriverManager.getConnection(url, dotenv.get("DB_USERNAME"), dotenv.get("DB_PASS"));
            initDaos();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al acceder a la bbdd, cambia el .env");
            return false;
        }
    }

    public PedidosSqlDao pedidos() {
        return pedidosDao;
    }

    public ClientesSqlDao clientes() {
        return clientesDao;
    }

    public ProductosSqlDao productos() {
        return productosDao;
    }

    @Override
    public void close() throws SQLException {
        if (conn != null) {
            if (!conn.getAutoCommit())
                conn.commit();
            conn.close();
        }
    }
}
