package model;

import java.util.Optional;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import exceptions.ExcepcionDatoNoValido;
import exceptions.ExcepcionFormatoIncorrecto;
import util.IBuilder;

/**
 * Objeto que representa a uno de los clientes
 * según el script de la BBDD «Jardinería». Este
 * script declara los siguientes campos para la
 * tabla «clients»:
 * 
 * <ul>
 * <li>codigo_cliente INTEGER NOT NULL</li>
 * <li>nombre_cliente VARCHAR(50) NOT NULL</li>
 * <li>nombre_contacto VARCHAR(30) DEFAULT NULL</li>
 * <li>apellido_contacto VARCHAR(30) DEFAULT NULL</li>
 * <li>telefono VARCHAR(15) NOT NULL</li>
 * <li>fax VARCHAR(15) NOT NULL</li>
 * <li>linea_direccion1 VARCHAR(50) NOT NULL</li>
 * <li>linea_direccion2 VARCHAR(50) DEFAULT NULL</li>
 * <li>ciudad VARCHAR(50) NOT NULL</li>
 * <li>region VARCHAR(50) DEFAULT NULL</li>
 * <li>pais VARCHAR(50) DEFAULT NULL</li>
 * <li>codigo_postal VARCHAR(10) DEFAULT NULL</li>
 * <li>codigo_empleado_rep_ventas INTEGER DEFAULT NULL</li>
 * <li>limite_credito NUMERIC(15,2) DEFAULT NULL</li>
 * </ul>
 */
@Entity
public class Cliente {
    /*
     * Al llamar a los atributos de la misma manera que las columnas
     * de la BBDD, no hace falta usar la etiqueta @Column.
     */

    private @Id Integer codigo_cliente;
    private String nombre_cliente;
    
    private @Embedded Contacto contacto;
    private @Embedded Domicilio domicilio;

    private Integer codigo_empleado_rep_ventas;
    private Double limite_credito;
    
    private @Transient TipoDocumento tipo_doc;
    private @Transient String dni;
    private @Transient String email;
    private @Transient String contrasena;

    private Cliente() {} // hibernate
    private Cliente(int codigo, String nombre, Contacto contacto,  Domicilio domicilio) {
        this.codigo_cliente = codigo;
        this.nombre_cliente = nombre;
        this.contacto = contacto;
        this.domicilio = domicilio;

        if (contacto != null) {
            contacto.nombre_contacto = null;
            contacto.apellido_contacto = null;
        }

        if (domicilio != null) {
            domicilio.linea_direccion2 = null;
            domicilio.region = null;
            domicilio.pais = null;
            domicilio.codigo_postal = null;
        }

        codigo_empleado_rep_ventas = null;
        limite_credito = null;
        tipo_doc = null;
        dni = null;
        email = null;
        contrasena = null;
    }

    /** Devuelve el código de cliente. */
    public Integer get_codigo() {
        return codigo_cliente;
    }

    /** 
     * Devuelve el código del empleado representante de 
     * ventas.
     */
    public Optional<Integer> get_cod_empl_rep_ventas() {
        return Optional.ofNullable(codigo_empleado_rep_ventas);
    }

    /**
     * Devuelve el límite de crédito.
     */
    public Optional<Double> get_limite_credito() {
        return Optional.ofNullable(limite_credito);
    }

    /** Devuelve el nombre. Nunca es null. */
    public String get_nombre() {
        return nombre_cliente;
    }

    /** Devuelve los datos de contacto. Nunca es null. */
    public Contacto get_contacto() {
        return contacto;
    }

    /** Devuelve los datos de domicilio. Nunca es null. */
    public Domicilio get_domicilio() {
        return domicilio;
    }

    /** 
     * Retorna el tipo de documento, que puede ser
     * DNI o NIE.
     */
    public Optional<TipoDocumento> get_tipo_documento() {
        return Optional.ofNullable(tipo_doc);
    }

    /** Devuelve el DNI o NIE */
    public Optional<String> get_dni() {
        return Optional.ofNullable(dni);
    }

    /** Devuelve el email */
    public Optional<String> get_email() {
        return Optional.ofNullable(email);
    }

    /** Devuelve la contraseña */
    public Optional<String> get_contrasena() {
        return Optional.ofNullable(contrasena);
    }

    /**
     * Representa los datos de contacto de un cliente.
     * Opcionalmente contendrá nombre y apellido de contacto,
     * pero siempre teléfono y fax.
     */
    @Embeddable
    public static class Contacto {

        private String nombre_contacto;
        private String apellido_contacto;
        private String telefono;
        private String fax;

        private Contacto() {} // hibernate
        private Contacto(String telefono, String fax) {
            this.telefono = telefono;
            this.fax = fax;
            nombre_contacto = null;
            apellido_contacto = null;
        }

        /** Nombre del contacto del cliente. */
        public Optional<String> nombre() {
            return Optional.ofNullable(nombre_contacto);
        }

        /** Apellido del contacto del cliente. */
        public Optional<String> apellido() {
            return Optional.ofNullable(apellido_contacto);
        }

        /** Teléfono del cliente. Nunca es null. */
        public String telefono() {
            return telefono;
        }

        /** Fax del cliente. Nunca es null. */
        public String fax() {
            return fax;
        }

        @Override
        public String toString() {
            return "Contacto [apellido=" + apellido_contacto + ", fax=" + fax + ", nombre=" + nombre_contacto + ", telefono=" + telefono
                    + "]";
        }
    }

    /**
     * Representa los datos de domicilio de un cliente.
     * Contienen, obligatoriamente, una línea de dirección
     * y la ciudad. Además, pueden tener una segunda línea
     * de dirección, un código postal (cp), una región y
     * un país.
     */
    @Embeddable
    public static class Domicilio {

        private String linea_direccion1;
        private String ciudad;
        private String linea_direccion2;
        private String region;
        private String pais;
        private String codigo_postal;

        private Domicilio() {} // hibernate
        private Domicilio(String direccion1, String ciudad) {
            this.linea_direccion1 = direccion1;
            this.ciudad = ciudad;
            linea_direccion2 = null;
            region = null;
            pais = null;
            codigo_postal = null;
        }

        /** 
         * Devuelve la primera línea de dirección del domicilio 
         * del cliente. Nunca es null.
         */
        public String direccion1() {
            return linea_direccion1;
        }

        /** Devuelve la ciudad del cliente. Nunca es null. */
        public String ciudad() {
            return ciudad;
        }

        /** 
         * Devuelve la segunda línea de dirección del domicilio
         * del cliente.
         */
        public Optional<String> direccion2() {
            return Optional.ofNullable(linea_direccion2);
        }

        /** Devuelve la región del domicilio del cliente. */
        public Optional<String> region() {
            return Optional.ofNullable(region);
        }

        /** Devuelve el país del domicilio del cliente. */
        public Optional<String> pais() {
            return Optional.ofNullable(pais);
        }

        /** Devuelve el código postal del domicilio del cliente */
        public Optional<String> cp() {
            return Optional.ofNullable(codigo_postal);
        }

        @Override
        public String toString() {
            return "Domicilio [ciudad=" + ciudad + ", cp=" + codigo_postal + ", direccion1=" + linea_direccion1 + ", direccion2="
                    + linea_direccion2 + ", pais=" + pais + ", region=" + region + "]";
        }
    }

    /** Clase para buildear instancias válidas de Cliente. */
    public static class Builder implements IBuilder<Cliente> {
        private Cliente cliente;

        /**
         * Constructor para asegurar la validez de un Cliente
         * obtenido por otros medios, como puede ser una 
         * deserialización desde un archivo json.
         * @param otro Un cliente que no se sabe si es válido.
         */
        public Builder(Cliente otro) {
            cliente = otro;
            
            // En este caso, solo hace falta rellenar los Optional
            // con el valor null (porque si no serían 
            // null)
            if (cliente.contacto != null) {
                if (cliente.contacto.nombre_contacto == null) {
                    cliente.contacto.nombre_contacto = null;
                }
                if (cliente.contacto.apellido_contacto == null) {
                    cliente.contacto.apellido_contacto = null;
                }
            }
            if (cliente.domicilio != null) {
                if (cliente.domicilio.linea_direccion2 == null) {
                    cliente.domicilio.linea_direccion2 = null;
                }
                if (cliente.domicilio.region == null) {
                    cliente.domicilio.region = null;
                }
                if (cliente.domicilio.pais == null) {
                    cliente.domicilio.pais = null;
                }
                if (cliente.domicilio.codigo_postal == null) {
                    cliente.domicilio.codigo_postal = null;
                }
            }
        }

        /**
         * Toma los datos obligatorios para formar un cliente válido.
         * Crea un Builder con esos datos. Notar que los parámetros aquí
         * aportados pueden ser null, y será la función «build» quien
         * lance la excepción.
         */
        public Builder(Integer codigo, String nombre, String telefono, 
                       String fax, String direccion1, String ciudad) {
            cliente = new Cliente(codigo, 
                                  nombre, 
                                  new Contacto(telefono, fax), 
                                  new Domicilio(direccion1, ciudad));
        }

        /** Para aportar un nombre de contacto al builder. */
        public Builder con_nombre_de_contacto(String nombre) {
            cliente.contacto.nombre_contacto = nombre;
            return this;
        }

        /** Para aportar un apellido de contacto al builder. */
        public Builder con_apellido_de_contacto(String apellido) {
            cliente.contacto.apellido_contacto = apellido;
            return this;
        }

        /** Para aportar un límite de crédito al builder. */
        public Builder con_limite_credito(Double limite_credito) {
            cliente.limite_credito = limite_credito;
            return this;
        }

        /** Para aportar un código de empleado rep. ventas al builder. */
        public Builder con_cod_empl_rep_ventas(Integer cod_empl_rep_ventas) {
            cliente.codigo_empleado_rep_ventas = cod_empl_rep_ventas;
            return this;
        }

        /** 
         * Para aportar una segunda línea de dirección al domicilio
         * del builder.
         */
        public Builder con_linea_direccion2(String direccion2) {
            cliente.domicilio.linea_direccion2 = direccion2;
            return this;
        }

        /** Para aportar una región al domicilio del builder. */
        public Builder con_region(String region) {
            cliente.domicilio.region = region;
            return this;
        }

        /** Para aportar un país al domicilio del builder. */
        public Builder con_pais(String pais) {
            cliente.domicilio.pais = pais;
            return this;
        }

        /** Para aportar un código postal al domicilio del builder. */
        public Builder con_codigo_postal(String cp) {
            cliente.domicilio.codigo_postal = cp;
            return this;
        }

        /** Para aportar DNI o NIE al builder */
        public Builder con_documento(TipoDocumento tipo, String documento) {
            cliente.tipo_doc = tipo;
            cliente.dni = documento;
            return this;
        }

        /** Para aportar email y contraseña al builder */
        public Builder con_email(String email, String contrasena) {
            cliente.email = email;
            cliente.contrasena = contrasena;
            return this;
        }

        /**
         * Se asegura de que ninguno de los campos «NOT NULL» del script SQL tienen su
         * valor por defecto (null, 0 o 0.0). Si alguno lo tiene, lanza una excepción y
         * devuelve null. Si no, devuelve un cliente válido con todos los datos
         * aportados al builder.
         * 
         * @return Un cliente válido o null si alguno de los datos es incorrecto.
         * @throws ExcepcionFormatoIncorrecto
         */
        public Cliente build() throws ExcepcionDatoNoValido, ExcepcionFormatoIncorrecto {
            boolean datos_necesarios_asignados = true;
            datos_necesarios_asignados &= (cliente.codigo_cliente != 0) &&
                      (cliente.nombre_cliente != null) &&
                      (cliente.contacto.telefono != null) &&
                      (cliente.contacto.fax != null) &&
                      (cliente.domicilio.linea_direccion1 != null) &&
                      (cliente.domicilio.ciudad != null);

            if (!datos_necesarios_asignados) {
                throw new ExcepcionDatoNoValido("Ninguno de los siguientes campos puede tener " +
                                                "su valor por defecto: codigo, nombre, telefono, " +
                                                "fax, direccion1, ciudad.");
            }

            if (cliente.email != null) {
                if (cliente.contrasena == null) {
                    throw new ExcepcionDatoNoValido("Deberia haber contraseña!!!");
                }
                // Comprobar que el email es en forma tal @ tal . tal
                if (!cliente.email.matches("\\w+@\\w+[.]\\w+")) {
                    throw new ExcepcionFormatoIncorrecto("El email debería matchear \"\\w+@\\w+[.]\\w+\", " +
                                                         "pero es " + cliente.email);
                }
            }

            if (cliente.tipo_doc != null) {
                if (cliente.dni == null) {
                    throw new ExcepcionDatoNoValido("Debería haber documento!!!");
                } else {
                    switch (cliente.tipo_doc) {
                    case DNI: // Comprobar que el documento es 8 dígitos + letra
                        if (!cliente.dni.matches("\\d{8}[a-zA-Z]")) {
                            throw new ExcepcionFormatoIncorrecto("El formato DNI debería cumplir \"[0-9]{8}[a-zA-Z]\", " +
                                                                 "pero es " + cliente.dni);
                        }
                        break;
                    case NIE: // Comprobar que el documento es letra + 7 dígitos + letra
                        if (!cliente.dni.matches("[a-zA-Z]\\d{7}[a-zA-Z]")) {
                            throw new ExcepcionFormatoIncorrecto("El formato NIE debería cumplir \"[a-zA-Z][0-9]{7}[a-zA-Z]\", " +
                                                                 "pero es " + cliente.dni);
                        }
                        break;
                    }
                }
            }
            
            return cliente;
        }
    }

    /** Utilidad para las tablas de la interfaz de usuario */
    public Object[] objArray() {
        return new String[] {
            String.valueOf(codigo_cliente),
            nombre_cliente,
            get_contacto().nombre().orElse(""),
            get_contacto().apellido().orElse(""),
            contacto.telefono,
            contacto.fax,
            domicilio.linea_direccion1,
            get_domicilio().direccion2().orElse(""),
            domicilio.ciudad,
            get_domicilio().region().orElse(""),
            get_domicilio().pais().orElse(""),
            get_domicilio().cp().orElse(""),
            get_cod_empl_rep_ventas().isPresent() ?  String.valueOf(get_cod_empl_rep_ventas().get()) : "",
            get_limite_credito().isPresent() ? String.valueOf(get_limite_credito().get()) : "",
            get_tipo_documento().isPresent() ? get_tipo_documento().get().toString() : "",
            get_dni().orElse(""),
            get_email().orElse(""),
            get_contrasena().orElse("")
        };
    }

    /** Un resumen del cliente para la práctica 1 de AD */
    public String infoResumen() {
        return "ID: " + codigo_cliente + " | Nombre: " + nombre_cliente + " | Contacto: " + get_contacto().nombre().orElse("-----") + " " + get_contacto().apellido().orElse("-----");
    }

    @Override
    public String toString() {
        return "\n============[ Cliente " + codigo_cliente + " ]============"
            + "\nNombre: " + nombre_cliente
            + "\nContacto {"
            + "\n  Nom: " + get_contacto().nombre().orElse("------")
            + "\n  Ape: " + get_contacto().apellido().orElse("------")
            + "\n  Tlf: " + contacto.telefono
            + "\n  Fax: " + contacto.fax
            + "\n}"
            + "\nDomicilio {"
            + "\n  Ln1: " + domicilio.linea_direccion1
            + "\n  Ln2: " + get_domicilio().direccion2().orElse("------")
            + "\n  Ciu: " + domicilio.ciudad
            + "\n  Reg: " + get_domicilio().region().orElse("------")
            + "\n  Pai: " + get_domicilio().pais().orElse("------")
            + "\n   CP: " + get_domicilio().cp().orElse("------")
            + "\n}"
            + "\nRpVtas: " + (get_cod_empl_rep_ventas().isPresent() ? get_cod_empl_rep_ventas().get() : "------")
            + "\nLimCrd: " + (get_limite_credito().isPresent() ? get_limite_credito().get() : "------");
    }
}
