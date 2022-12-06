import java.security.IdentityScope;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Sistema {

    private static Scanner entrada = new Scanner(System.in);
    private static ArrayList<Empresa> empresas = new ArrayList<Empresa>();

    public static void listarEmpresas(){
        for (int i = 0; i <empresas.size() ; i++) {
            System.out.println(empresas.get(i).toString());
        }
    }
    public static void inicializarDatos(){
        Empresa unaEmpresa = new Empresa(1, "Agricola");
        ArrayList<Animal> animales = new ArrayList<Animal>();
        animales.add(new Animal(1, 'M', true, true, "Vacuno", null, null));
        animales.add(new Animal(2, 'H', true, true, "Vacuno", null, null));
        animales.add(new Animal(3, 'M', true, true, "Ovino", null, null));
        animales.add(new Animal(4, 'H', true, true, "Ovino", null, null));
        unaEmpresa.getUsuarios().add(new Usuario(1, "admin", "admin"));
        unaEmpresa.setAnimales(animales);
        empresas.add(unaEmpresa);
    }
    public static void main(String[] args) {
        try {
            inicializarDatos();
            menuPrincipal();
        } catch (Exception e) {
            System.out.println("No se pudo acceder");
            main(args);
        }
    }
    private static void menuPrincipal(){
        try {
            System.out.println("** MENÚ PRINCIPAL **");
            listarEmpresas();
            System.out.println("Seleccione una de las empresas listadas o ingrese -1 para administrarlas");
            int opcion = Integer.parseInt(entrada.next());
            entrada.nextLine(); // Necesario para desbuggear el entrada.next()
            switch (opcion) {
                case -1:
                    administrarEmpresas();
                    break;
                default:
                    login(opcion);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Hubo un problema con la operación");
            menuPrincipal();
        }

    }

    // EMPRESAS

    private static void administrarEmpresas(){
        try {
            System.out.println("Administración empresas (0 para salir):");
            System.out.println("1. Alta");
            System.out.println("2. Eliminar");
            System.out.println("3. Modificar");
            int opcion = Integer.parseInt(entrada.next());
            entrada.nextLine(); // Necesario para desbuggear el entrada.next()

            switch (opcion) {
                case 1:
                    altaEmpresa();
                    break;
                case 2:
                    bajaEmpresa();
                    break;
                case 3:
                    modificarEmpresa();
                    break;            
                default:
                    menuPrincipal();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error en el programa");
            administrarEmpresas();
        }
    }
    private static int generarIdEmpresa(){
        return empresas.size()+1;
    }
    private static void altaEmpresa() {
        try {
            System.out.println("Alta de empresa");
            System.out.println("Ingrese nombre de la empresa (0 para salir)");
            String nombre = entrada.nextLine();
            if(!nombre.equals("0")){

                Empresa unaEmpresa = new Empresa(generarIdEmpresa(), nombre);
                System.out.println("Agregando datos iniciales");
                unaEmpresa.getAnimales().add(new Animal(generarIdAnimal(), 'M', true, true, "Vacuno", null, null));
                unaEmpresa.getAnimales().add(new Animal(generarIdAnimal(), 'H', true, true, "Vacuno", null, null));
                unaEmpresa.getAnimales().add(new Animal(generarIdAnimal(), 'M', true, true, "Ovino", null, null));
                unaEmpresa.getAnimales().add(new Animal(generarIdAnimal(), 'H', true, true, "Ovino", null, null));
                empresas.add(unaEmpresa);
                
                System.out.println("Empresa agregada con éxito");
                administrarEmpresas();
            }
            else{
                administrarEmpresas();
            }
            
        } catch (Exception e) {
            System.out.println("No se pudo realizar la operación");
            altaEmpresa();
        }
    }

    private static void bajaEmpresa(){
        try {
            
            System.out.println("Baja de empresa");
            listarEmpresas();
            System.out.println("Elija una opción de las listadas a eliminar (0 para salir)");
            int opcion = Integer.parseInt(entrada.next());
            entrada.nextLine(); // Necesario para desbuggear el entrada.next()
            if(opcion!=0){

                empresas.remove(opcion-1);
                System.out.println("Empresa eliminada con éxito");
                administrarEmpresas();
            }
            else{
                administrarEmpresas();
            }
        } catch (Exception e) {
            System.out.println("No se pudo realizar la operación");
            bajaEmpresa();
        }
    }
 
    private static void modificarEmpresa(){
        try {
            System.out.println("Modificación de empresas");
            listarEmpresas();
            System.out.println("Elija la empresa de las listadas a modificar (0 para salir)");
            int opcion = Integer.parseInt(entrada.next());
            entrada.nextLine(); // Necesario para desbuggear el entrada.next()
            if(opcion!=0){

                System.out.println("Ingrese nombre:");
                String nombreEmp = entrada.nextLine();
                empresas.get(opcion-1).setNombreEmpresa(nombreEmp);
                System.out.println("Empresa modificada con éxito.");
                administrarEmpresas();
            }
            else{
                administrarEmpresas();
            }
        } catch (Exception e) {
            System.out.println("No se pudo realizar la operación");
            modificarEmpresa();
        }
    }
    // USUARIOS
    private static void login(int idEmpresa) {
        try {
            System.out.println("Log-In");
            System.out.println("Ingreso al sistema (0 para salir)");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrar usuario");
            int opcion = Integer.parseInt(entrada.next());
            entrada.nextLine(); // Necesario para desbuggear el entrada.next()
            if (opcion == 1)
                iniciarSesion(idEmpresa);
            else if(opcion==2)
                registrarUsuario(idEmpresa);
            else if (opcion ==0)
                menuPrincipal();
            else{
                System.out.println("Ingrese una opción válida");
                login(idEmpresa);
            }

        } catch (Exception e) {
            System.out.println("No se pudo acceder a la sección");
            login(idEmpresa);
        }
    }

    private static void iniciarSesion(int idEmpresa) {
        try {
            boolean contr = false;
            System.out.println("Inicio de sesión");
            System.out.println("Ingrese usuario: (0 para salir)");
            String usuario = entrada.nextLine();
            
            if(!usuario.equals("0")){
                System.out.println("Ingrese contraseña");
                String contrasena = entrada.nextLine();
                Empresa empresa = empresas.get(idEmpresa-1);
                for (int i = 0; i < empresa.getUsuarios().size(); i++) {
                    if (usuario.equals(empresa.getUsuarios().get(i).getUsuario())
                            && contrasena.equals(empresa.getUsuarios().get(i).getContraseña())) {
                        contr = true;
                        System.out.println("Inició sesión con éxito");
                        menuAnimales(idEmpresa);
                    }
                }
                if (contr == false) {

                    System.out.println("No se pudo iniciar sesión.");
                    login(idEmpresa);
                }
            }
            else{
                login(idEmpresa);
            }
        
        } catch (Exception e) {
            System.out.println("No se pudo iniciar sesión.");
            iniciarSesion(idEmpresa);
        }
    }

    private static void registrarUsuario(int idEmpresa) {
        try {
            System.out.println("Registro de usuario");
            System.out.println("Ingrese usuario: (0 para salir)");
            String usuario = entrada.nextLine();
            
            if(!usuario.equals("0")){
            System.out.println("Ingrese contraseña");
            String contrasena = entrada.nextLine();
            System.out.println("Confirme contraseña:");
            String confContr = entrada.nextLine();
            Empresa empresa = empresas.get(idEmpresa-1);

                if (contrasena.equals(confContr)) {
                    ArrayList<Usuario> listaUsuarios = empresa.getUsuarios();
                    listaUsuarios.add(new Usuario(generarIdUsuario(), usuario, contrasena));
                    empresa.setUsuarios(listaUsuarios);
                    System.out.println("Usuario agregado con éxito");
                    login(idEmpresa);
                } else {
                    throw new Exception();
                }
            }
            else{
                login(idEmpresa);
            }
        } catch (Exception e) {
            System.out.println("No se pudo registrar el usuario, inténtelo nuevamente.");
            registrarUsuario(idEmpresa);
        }
    }

    private static int generarIdUsuario() {
        return buscarTodosLosUsuarios().size()+1;
    }

    // ANIMALES

    private static void menuAnimales(int idEmpresa) {
        try {
            System.out.println("Menú principal animales.");
            System.out.println("Ingrese una opción: ");
            System.out.println("1. Administrar animales.");
            System.out.println("2. Agregar vacunas y desparasitación.");
            System.out.println("3. Listar separando por especie.");
            System.out.println("4. Listar genealogía por identificador.");
            System.out.println("5. Listar animal por identificador.");
            System.out.println("0. Volver");
            int opcion = Integer.parseInt(entrada.next());
            entrada.nextLine(); // Necesario para desbuggear el entrada.next()
            switch (opcion) {
                case 1: {
                    administracionAnimales(idEmpresa);
                    break;
                }
                case 2:{
                    vacunasYDesparasitacion(idEmpresa);
                    break;
                }
                case 3:{
                    listarPorEspecie(idEmpresa);
                    System.out.println("Ingrese cualquier tecla para volver al menú");
                    entrada.next();
                    menuAnimales(idEmpresa);
                    break;
                }
                case 4:{
                    System.out.println("Ingrese el id del animal a buscar genealogía");
                    int idAnimal = Integer.parseInt(entrada.next());
                    entrada.nextLine();
                    System.out.println("Listando para ID " +idAnimal);
                    listarGenealogia(buscarAnimalPorId(idAnimal));
                    System.out.println("Ingrese cualquier tecla para volver al menú");
                    entrada.next();
                    menuAnimales(idEmpresa);
                    break;
                }
                case 5:{
                    System.out.println("Ingrese el id del animal a buscar");
                    int idAnimal = Integer.parseInt(entrada.next());
                    entrada.nextLine();
                    System.out.println(buscarAnimalPorId(idAnimal));
                    System.out.println("Ingrese cualquier tecla para volver al menú");
                    entrada.next();
                    menuAnimales(idEmpresa);
                    break;
                }

                default: {
                    login(idEmpresa);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("No se pudo realizar la operación");
            menuAnimales(idEmpresa);
        }
    }

    private static void administracionAnimales(int idEmpresa) {
        try {
            System.out.println("Administración de animales");
            System.out.println("Ingrese una opción: ");
            System.out.println("1. Agregar animal");
            System.out.println("2. Eliminar animal");
            System.out.println("3. Modificar animal");
            System.out.println("0. Volver");
            int opcion = Integer.parseInt(entrada.next());
            entrada.nextLine(); // Necesario para desbuggear el entrada.next()

            switch (opcion) {
                case 1: {
                    agregarAnimal(idEmpresa);
                    break;
                }
                case 2: {
                    eliminarAnimal(idEmpresa);
                    break;
                }
                case 3: {
                    modificarAnimal(idEmpresa);
                    break;
                }
                default: {
                    menuAnimales(idEmpresa);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("No se pudo realizar la operación");
            administracionAnimales(idEmpresa);
        }
    }

    private static int generarIdAnimal() {
        return buscarTodosLosAnimales().size()+1;
    }

    private static void agregarAnimal(int idEmpresa) {
        try {
            System.out.println("Agregar animal");
            System.out.println("Ingrese sexo del animal M o H (0 para salir)");
            char sexo = entrada.nextLine().charAt(0);
            if(sexo!='0'){
                System.out.println("¿El animal ha sido desparasitado? (S/N)");
                char desparasitado = entrada.nextLine().charAt(0);
                System.out.println("¿El animal ha sido vacunado? (S/N)");
                char vacunado = entrada.nextLine().charAt(0);
                int tipo=0;
                do {
                    System.out.println("Seleccione número de tipo: 1. Vacuno, 2. Ovino");
                    tipo = Integer.parseInt(entrada.next());
                    entrada.nextLine(); // Necesario para desbuggear el entrada.next()
                } while (tipo!=1 && tipo!=2);
    
                String tipoS ="";
                if(tipo==1){
                    tipoS="Vacuno";
                }
                else if(tipo==2){
                    tipoS="Ovino";
                }
    
                listarPorSexoyTipo(idEmpresa, 'M', tipoS);
                System.out.println("Seleccione el número del padre de los animales de arriba:");
                int padre = Integer.parseInt(entrada.next());
                entrada.nextLine(); // Necesario para desbuggear el entrada.next()
    
                listarPorSexoyTipo(idEmpresa, 'H', tipoS);
                System.out.println("Seleccione el número de la madre de los animales de arriba:");
                int madre = Integer.parseInt(entrada.next());
                entrada.nextLine(); // Necesario para desbuggear el entrada.next()
    
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
    

                Empresa empresa = empresas.get(idEmpresa-1);
                
                Animal APadre = empresa.getAnimales().get(padre-1);
                Animal AMadre = empresa.getAnimales().get(madre-1);
                empresa.getAnimales().add(new Animal(generarIdAnimal(), String.valueOf(sexo).toUpperCase().charAt(0), desparasitadoB, vacunadoB, tipoS, APadre, AMadre));
                System.out.println("Animal agregado con éxito");
                administracionAnimales(idEmpresa);
            }
            else{
                administracionAnimales(idEmpresa);
            }
            
        } catch (Exception e) {
            System.out.println("No se pudo realizar la operación");
            agregarAnimal(idEmpresa);
        }
    }

    private static void eliminarAnimal(int idEmpresa) {
        try {
            System.out.println("Eliminar animales");
            listarAnimales(idEmpresa);
            System.out.println("Ingrese número de animal a eliminar (0 para salir)");
            int opcion = Integer.parseInt(entrada.next());
            entrada.nextLine(); // Necesario para desbuggear el entrada.next()
            if(opcion!=0){

                Empresa empresa = empresas.get(idEmpresa-1);
                empresa.getAnimales().remove(opcion-1);
                administracionAnimales(idEmpresa);
            }
            else{
                administracionAnimales(idEmpresa);
            }
        } catch (Exception e) {
            System.out.println("No se pudo realizar la operación");
            eliminarAnimal(idEmpresa);
        }
    }

    private static void modificarAnimal(int idEmpresa) {
        try {
            System.out.println("Modificar animales");
            listarAnimales(idEmpresa);

            System.out.println("Ingrese número de animal a modificar (0 para salir)");
            int opcion = Integer.parseInt(entrada.next());
            entrada.nextLine(); // Necesario para desbuggear el entrada.next()
            if(opcion!=0){
                
                Empresa empresa = empresas.get(idEmpresa-1);
                Animal temp = empresa.getAnimales().get(opcion-1);
                System.out.println("Ingrese sexo del animal (M o H)");
                System.out.println("Anterior valor: " + temp.getSexo());
                char sexo = String.valueOf(entrada.nextLine().charAt(0)).toUpperCase().charAt(0);
                empresa.getAnimales().get(opcion-1).setSexo(String.valueOf(sexo).toUpperCase().charAt(0));
                System.out.println("Ingrese el número de tipo: 1. Vacuno, 2. Ovino");
                System.out.println("Anterior valor: " + temp.getTipo());
                int tipo = Integer.parseInt(entrada.next());
                entrada.nextLine(); // Necesario para desbuggear el entrada.next()
                empresa.getAnimales().get(opcion-1).setSexo(sexo);
                System.out.println("¿El animal ha sido desparasitado? (S/N)");
                System.out.println("Anterior valor: " + temp.isDesparasitado());
                char desparasitado = entrada.nextLine().charAt(0);
                System.out.println("¿El animal ha sido vacunado? (S/N)");
                System.out.println("Anterior valor: " + temp.isVacunado());
                char vacunado = entrada.nextLine().charAt(0);

                String tipoS = "";
                if(tipo==1)
                    tipoS="Vacuno";
                else if(tipo==2)
                    tipoS="Ovino";

                listarPorSexoyTipo(idEmpresa, 'M', tipoS);
                System.out.println("Seleccione el número del padre de los animales de arriba:");
                System.out.println("Anterior valor: " + temp.getPadre().toString());
                int padre = Integer.parseInt(entrada.next());
                entrada.nextLine(); // Necesario para desbuggear el entrada.next()
                listarPorSexoyTipo(idEmpresa, 'H', tipoS);
                System.out.println("Seleccione el número de la madre de los animales de arriba:");
                System.out.println("Anterior valor: " + temp.getMadre().toString());
                int madre = Integer.parseInt(entrada.next());
                entrada.nextLine(); // Necesario para desbuggear el entrada.next()

                boolean desparasitadoB;
                if (String.valueOf(desparasitado).toUpperCase().charAt(0) == 'S')
                    desparasitadoB = true;
                else
                    desparasitadoB = false;

                temp.setDesparasitado(desparasitadoB);

                boolean vacunadoB;
                if (String.valueOf(vacunado).toUpperCase().charAt(0) == 'S')
                    vacunadoB = true;
                else
                    vacunadoB = false;

                temp.setVacunado(vacunadoB);

                Animal APadre = empresa.getAnimales().get(padre-1);
                if(!(APadre.getTipo()==tipoS) || !(APadre.getSexo()=='M'))
                    throw new Exception();
                
                Animal AMadre = empresa.getAnimales().get(madre-1);

                if(!(AMadre.getTipo()==tipoS) || !(AMadre.getSexo()=='H'))
                    throw new Exception();
                empresa.getAnimales().get(opcion-1).setMadre(AMadre);
                empresa.getAnimales().get(opcion-1).setPadre(APadre);
                System.out.println("Animal modificado con éxito");
                administracionAnimales(idEmpresa);
            }
            else{
                administracionAnimales(idEmpresa);
            }
        } catch (Exception e) {
            System.out.println("No se pudo realizar la operación");
            modificarAnimal(idEmpresa);
        }
    }

    private static void vacunasYDesparasitacion(int idEmpresa){
        
        try{
            System.out.println("Agregar vacunas y desparasitación");
            listarAnimales(idEmpresa);
            System.out.println("Seleccione nro de animal de los listados arriba (0 para salir, -1 para listar)");
            int opcion = Integer.parseInt(entrada.next());
            entrada.nextLine(); // Necesario para desbuggear el entrada.next()
            if(opcion>0){
                Empresa empresa = empresas.get(idEmpresa-1);
                Animal unAnimal = empresa.getAnimales().get(opcion-1);
                if(unAnimal.isDesparasitado()){
                    System.out.println("Ingrese la fecha de desparasitación (DD/MM/AAAA).");
                    Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(entrada.nextLine());
                    unAnimal.setFechaDesparasitado(fecha);
                }
                else{
                    System.out.println("El animal no está disparasitado, modifique el animal antes a editar la fecha.");
                }
                if(unAnimal.isVacunado()){
                    System.out.println("Ingrese la fecha de vacunación (DD/MM/AAAA).");
                    Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(entrada.nextLine());
                    unAnimal.setFechaVacunado(fecha);
                }
                else{
                    System.out.println("El animal no está vacunado, modifique el animal antes a editar la fecha.");
                }
                System.out.println("Fechas administradas con éxito.");
                menuAnimales(idEmpresa);
            }
            else if(opcion==-1){
                listarAnimalesConFecha(idEmpresa);
                vacunasYDesparasitacion(idEmpresa);
            }
            else{
                menuAnimales(idEmpresa);
            }
        }
        catch(Exception e){
            System.out.println("No se pudo realizar la operación");
            vacunasYDesparasitacion(idEmpresa);
        }
    }
    
    // Listados

    private static void listarAnimales(int idEmpresa) {
        Empresa empresa = empresas.get(idEmpresa-1);
        for (int i = 0; i < empresa.getAnimales().size(); i++) {
            System.out.println(empresa.getAnimales().get(i).toString());
        }
    }

    private static void listarAnimalesConFecha(int idEmpresa) {
        Empresa empresa = empresas.get(idEmpresa-1);
        for (int i = 0; i < empresa.getAnimales().size(); i++) {
            System.out.println(empresa.getAnimales().get(i).toStringConFecha());
        }
    }
    
    private static void listarPorSexoyTipo(int idEmpresa, char sexo, String tipo) {
        Empresa empresa = empresas.get(idEmpresa-1);

        for (int i = 0; i < empresa.getAnimales().size(); i++) {
            if (empresa.getAnimales().get(i).getSexo() == sexo && empresa.getAnimales().get(i).getTipo()==tipo)
                System.out.println(empresa.getAnimales().get(i).toString());
        }
    }

    private static void listarPorEspecie(int idEmpresa){
        Empresa empresa = empresas.get(idEmpresa-1);
        System.out.println("Vacunos:");
        for (int i = 0; i < empresa.getAnimales().size(); i++) {
            if(empresa.getAnimales().get(i).getTipo().equals("Vacuno")){
                System.out.println(empresa.getAnimales().get(i).toString());
            }
        }
        System.out.println("Ovinos:");
        for (int i = 0; i < empresa.getAnimales().size(); i++) {
            if(empresa.getAnimales().get(i).getTipo().equals("Ovino")){
                System.out.println(empresa.getAnimales().get(i).toString());
            }
        }
    }

    // Aplicamos conceptos de árbol binario
    private static void listarGenealogia(Animal animal){
        if(animal!=null){

            System.out.println("Padre de ID "+animal.getIdAnimal());
            System.out.println(animal.getPadre());
            
            System.out.println("Madre de ID "+animal.getIdAnimal());
            System.out.println(animal.getMadre());

            listarGenealogia(animal.getPadre());
            listarGenealogia(animal.getMadre());
            
        }
        
    }
    private static Animal buscarAnimalPorId(int idAnimal){
        ArrayList<Animal> listaCompleta = new ArrayList<Animal>();
        for (Empresa empresa : empresas) {
            listaCompleta.addAll(empresa.getAnimales());
        }
        Animal animal = buscarAnimal(idAnimal, listaCompleta, 0, listaCompleta.size()-1);
        return animal;
    }

    // Aplicamos recursividad y búsqueda binaria para el ID
    private static Animal buscarAnimal(int idAnimal, ArrayList<Animal> animales, int desde, int hasta){
        if(desde==hasta){
            if(animales.get(hasta).getIdAnimal()==idAnimal){
                return animales.get(hasta);
            }
            else{
                return null;
            }
        }
        else{
            int medio = (desde+hasta) / 2;
            if(animales.get(medio).getIdAnimal()<idAnimal){
                return buscarAnimal(idAnimal, animales, medio+1, hasta);
            }
            else{
                return buscarAnimal(idAnimal, animales, desde, medio);
            }
        }

    }

    // Utils extras

    private static ArrayList<Animal> buscarTodosLosAnimales(){
        ArrayList<Animal> todo = new ArrayList<Animal>();
        for (Empresa empresa : empresas) {
            todo.addAll(empresa.getAnimales());
        }
        return todo;
    }
    
    private static ArrayList<Usuario> buscarTodosLosUsuarios(){
        ArrayList<Usuario> todo = new ArrayList<Usuario>();
        for (Empresa empresa : empresas) {
            todo.addAll(empresa.getUsuarios());
        }
        return todo;
    }
}