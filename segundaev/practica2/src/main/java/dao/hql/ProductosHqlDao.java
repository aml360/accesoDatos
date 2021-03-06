package dao.hql;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import dao.IDao;
import exceptions.ExcepcionCodigoYaExistente;
import model.Producto;

public class ProductosHqlDao implements IDao<Producto> {

    private Session session;

    public ProductosHqlDao(Session session) {
        this.session = session;
    }

    @Override
    public Optional<Producto> uno(Object id) {
        return Optional.ofNullable(session.get(Producto.class, (String) id));
    }

    @Override
    public List<Producto> todos() {
        return session.createQuery("from Producto", Producto.class).list();
    }

    @Override
    public void guardar(Producto t) throws Exception {
        try {
            session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new ExcepcionCodigoYaExistente("ñ");
        }
    }

    @Override
    public void modificar(Producto t) {
        session.beginTransaction();
        session.evict(t);
        session.update(t);
        session.getTransaction().commit();
    }

    @Override
    public void eliminar(Producto t) {
        session.delete(t);
    }
    
}
