import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.SwingConstants;

import aed.ABB;

public class Sistema {

    private static Scanner entrada = new Scanner(System.in);
    private static Empresa empresaSelec = null;
    private static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    private static ArrayList<Animal> animales = new ArrayList<Animal>();

    private static void rellenarInfoInicial() {

        usuarios.clear();
        usuarios.add(new Usuario(1, "admin", "admin"));
        animales.add(new Animal(1, 'M', true, true, new Date("04/03/2020"), new Date("12/04/2019"), new Animal(),
                new Animal()));
        animales.add(new Animal(2, 'H', false, true, null, new Date("12/04/2019"), new Animal(), new Animal()));

    }

    public static void main(String[] args) {
        try {
            System.out.println("** MENÚ PRINCIPAL **");
            rellenarInfoInicial();
            altaEmpresa();
            login();
        } catch (Exception e) {
            System.out.println("No se pudo acceder");
        }
    }

    // EMPRESAS
    private static void altaEmpresa() {
        try {
            System.out.println("Ingrese nombre de la empresa");
            String nombre = entrada.nextLine();

            Empresa unaEmpresa = new Empresa(1, nombre);
            empresaSelec = unaEmpresa;
        } catch (Exception e) {
            System.out.println("No se pudo realizar la operación");
        }
    }

    // USUARIOS
    private static void login() {
        try {

            System.out.println("Ingreso al sistema");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrar usuario");
            int opcion = entrada.nextInt();
            entrada.nextLine();
            if (opcion == 1)
                iniciarSesion();
            else
                registrarUsuario();
        } catch (Exception e) {
            System.out.println("No se pudo acceder a la sección");
            login();
        }
    }

    private static void iniciarSesion() {
        try {
            boolean contr = false;
            System.out.println("Inicio de sesión");
            System.out.println("Ingrese usuario:");
            String usuario = entrada.nextLine();
            System.out.println("Ingrese contraseña");
            String contrasena = entrada.nextLine();
            for (int i = 0; i < usuarios.size(); i++) {
                if (usuario.equals(usuarios.get(i).getUsuario())
                        && contrasena.equals(usuarios.get(i).getContraseña())) {
                    contr = true;
                    System.out.println("Inició sesión con éxito");
                    menuAnimales();
                }
            }
            if (contr == false) {

                System.out.println("No se pudo iniciar sesión.");
                login();
            }

        } catch (Exception e) {
            System.out.println("No se pudo iniciar sesión.");
            iniciarSesion();
        }
    }

    private static void registrarUsuario() {
        try {
            System.out.println("Registro de usuario");
            System.out.println("Ingrese usuario:");
            String usuario = entrada.nextLine();
            System.out.println("Ingrese contraseña");
            String contrasena = entrada.nextLine();
            System.out.println("Confirme contraseña:");
            String confContr = entrada.nextLine();

            if (contrasena.equals(confContr)) {
                usuarios.add(new Usuario(generarIdUsuario(), usuario, contrasena));
                System.out.println("Usuario agregado con éxito");
                login();
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("No se pudo registrar el usuario, inténtelo nuevamente.");
            registrarUsuario();
        }
    }

    private static int generarIdUsuario() {
        return usuarios.size() - 1;
    }

    // ANIMALES

    private static void menuAnimales() {
        try {
            System.out.println("Ingrese una opción: ");
            System.out.println("1. Administrar animales.");
            System.out.println("2. Agregar vacunas y desparasitación.");
            System.out.println("3. Listar separando por especie.");
            System.out.println("4. Listar genealogía por identificador.");
            System.out.println("5. Listar animal por identificador.");
            int opcion = entrada.nextInt();
            entrada.nextLine();
            switch (opcion) {
                case 1: {
                    administracionAnimales();
                } /*
                   * case 2:{
                   * administrarVacunas();
                   * }
                   * case 3:{
                   * listarPorEspecie();
                   * }
                   * case 4:{
                   * listarGenPorIdentificador();
                   * }
                   * case 5:{
                   * listarPorIdentificador();
                   * }
                   */
            }
        } catch (Exception e) {
            System.out.println("No se pudo realizar la operación");
        }
    }

    private static void administracionAnimales() {
        try {
            System.out.println("Ingrese una opción: ");
            System.out.println("1. Agregar animal");
            System.out.println("2. Eliminar animal");
            System.out.println("3. Modificar animal");
            int opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1: {
                    agregarAnimal();
                }
                case 2: {
                    eliminarAnimal();
                }
                case 3: {
                    modificarAnimal();
                }
            }
        } catch (Exception e) {
            System.out.println("No se pudo realizar la operación");
        }
    }

    private static int generarIdAnimal() {

        return animales.size() - 1;
    }

    private static void agregarAnimal() {
        try {
            System.out.println("Ingrese sexo del animal");
            char sexo = entrada.nextLine().charAt(0);
            System.out.println("¿El animal ha sido desparasitado? (S/N)");
            char desparasitado = entrada.nextLine().charAt(0);
            System.out.println("¿El animal ha sido vacunado? (S/N)");
            char vacunado = entrada.nextLine().charAt(0);

            listarPorSexo('M');
            System.out.println("Seleccione el padre de los animales de arriba:");
            int padre = entrada.nextInt();
            entrada.nextLine();

            listarPorSexo('H');
            System.out.println("Seleccione la madre de los animales de arriba:");
            int madre = entrada.nextInt();
            entrada.nextLine();

            boolean desparasitadoB;
            if (String.valueOf(desparasitado).toUpperCase().charAt(0) == 'S')
                desparasitadoB = true;
            else
                desparasitadoB = false;

            boolean vacunadoB;
            if (String.valueOf(vacunado).toUpperCase().charAt(0) == 'S')
                vacunadoB = true;
            else
                vacunadoB = false;

            Animal APadre = buscarAnimal(padre);
            Animal AMadre = buscarAnimal(madre);

            animales.add(new Animal(generarIdAnimal(), sexo, desparasitadoB, vacunadoB, APadre, AMadre));
            System.out.println("Animal agregado con éxito");
            administracionAnimales();
        } catch (Exception e) {
            System.out.println("No se pudo realizar la operación"+e.getMessage());
        }
    }

    private static void eliminarAnimal() {
        try {
            listarAnimales();
            System.out.println("Ingrese número de animal a eliminar");
            int opcion = entrada.nextInt();
            entrada.nextLine();

            Animal animal = buscarAnimal(opcion);
            animales.remove(animal);
            administracionAnimales();
        } catch (Exception e) {
            System.out.println("No se pudo realizar la operación");
        }
    }

    private static void modificarAnimal() {
        try {
            listarAnimales();
            System.out.println("Ingrese número de animal a modificar");
            int opcion = entrada.nextInt();
            entrada.nextLine();

            Animal temp = buscarAnimal(opcion);
            System.out.println("Ingrese sexo del animal");
            System.out.println("Anterior valor: " + temp.getSexo());
            char sexo = entrada.nextLine().charAt(0);
            System.out.println("¿El animal ha sido desparasitado? (S/N)");
            System.out.println("Anterior valor: " + temp.isDesparasitado());
            char desparasitado = entrada.nextLine().charAt(0);
            System.out.println("¿El animal ha sido vacunado? (S/N)");
            System.out.println("Anterior valor: " + temp.isVacunado());
            char vacunado = entrada.nextLine().charAt(0);

            listarPorSexo('M');
            System.out.println("Seleccione el padre de los animales de arriba:");
            System.out.println("Anterior valor: " + temp.getPadre().toString());
            int padre = entrada.nextInt();
            entrada.nextLine();

            listarPorSexo('H');
            System.out.println("Seleccione la madre de los animales de arriba:");
            System.out.println("Anterior valor: " + temp.getMadre().toString());
            int madre = entrada.nextInt();
            entrada.nextLine();

            boolean desparasitadoB;
            if (String.valueOf(desparasitado).toUpperCase().charAt(0) == 'S')
                desparasitadoB = true;
            else
                desparasitadoB = false;

            boolean vacunadoB;
            if (String.valueOf(vacunado).toUpperCase().charAt(0) == 'S')
                vacunadoB = true;
            else
                vacunadoB = false;

            Animal APadre = buscarAnimal(padre);
            Animal AMadre = buscarAnimal(madre);

            animales.add(opcion, new Animal(temp.getIdAnimal(), sexo, desparasitadoB, vacunadoB, APadre, AMadre));
            administracionAnimales();
        } catch (Exception e) {
            System.out.println("No se pudo realizar la operación");
        }
    }

    private static Animal buscarAnimal(int pos) {
        return animales.get(pos);
    }

    // Listados animales

    private static void listarAnimales() {
        for (int i = 0; i < animales.size(); i++) {
            System.out.println(i + animales.get(i).toString());
        }
    }

    private static void listarPorSexo(char sexo) {
        for (int i = 0; i < animales.size(); i++) {
            if (animales.get(i).getSexo() == sexo)
                System.out.println(i + ". "+animales.get(i).toString());
        }
    }
}