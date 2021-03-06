package dao.hql;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import dao.IDao;
import exceptions.ExcepcionCodigoYaExistente;
import model.Pedido;

public class PedidosHqlDao implements IDao<Pedido> {

    private Session session;

    public PedidosHqlDao(Session session) {
        this.session = session;
    }

    @Override
    public Optional<Pedido> uno(Object id) {
        return Optional.ofNullable(session.get(Pedido.class, (Integer) id));
    }

    @Override
    public List<Pedido> todos() {
        return session.createQuery("from Pedido", Pedido.class).list();
    }

    @Override
    public void guardar(Pedido t) throws Exception {
        try {
            session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new ExcepcionCodigoYaExistente("u");
        }
    }

    @Override
    public void modificar(Pedido t) {
        session.beginTransaction();
        session.update(t);
        session.getTransaction().commit();
    }

    @Override
    public void eliminar(Pedido t) {
        session.delete(t);
    }
    
}
