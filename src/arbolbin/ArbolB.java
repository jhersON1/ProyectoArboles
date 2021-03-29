/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author neON
 * @param <K>
 * @param <V>
 */
public class ArbolB<K extends Comparable<K>, V> extends ArbolMViasBusqueda<K,V> {

    private int nroMaxDatos;
    private int nroMinDatos;
    private int nroMinHijos;

    public ArbolB() throws ExcepcionOrdenInvalido {
        super();
        this.nroMaxDatos = 2;
        this.nroMinDatos = 1;
        this.nroMinHijos = 2;
        //this(3);
    }

    public ArbolB(int orden) throws ExcepcionOrdenInvalido {
        super(orden + 1);
        this.nroMaxDatos = orden - 1;
        this.nroMinDatos = this.nroMaxDatos / 2;
        this.nroMinHijos = this.nroMinDatos + 1;
    }

    @Override
    public void insertar(K claveAInsertar, V valorAInsertar) {
        if (this.esArbolVacio()) {
            super.raiz = new NodoMVias<>(super.orden  , claveAInsertar,valorAInsertar);
            return;
        }
        Stack<NodoMVias<K,V>> pilaDeAncestros = new Stack<>();
        NodoMVias<K,V> nodoActual = this.raiz;
        while (!NodoMVias.esNodoVacio(nodoActual)) {
            int posicionClaveExistente = super.existeClaveEnNodo(nodoActual, claveAInsertar);
            if (posicionClaveExistente != POSICION_INVALIDA) {
                nodoActual.setValor(posicionClaveExistente, valorAInsertar);
                nodoActual = NodoMVias.nodoVacio();
            } else {
                if (nodoActual.esHoja()) {
                    super.insertarDatoOrdenadoEnNodo(nodoActual, claveAInsertar, valorAInsertar);
                    if (nodoActual.cantidadDeClavesNoVacias() > this.nroMaxDatos) {
                        this.dividir(nodoActual, pilaDeAncestros);
                    }
                    nodoActual = NodoMVias.nodoVacio();
                }else {
                    int posicionPorDondeBajar = super.posicionPorDondeBajar(nodoActual, claveAInsertar);
                    pilaDeAncestros.push(nodoActual);
                    nodoActual = nodoActual.getHijo(posicionPorDondeBajar);
                }
            }
        }
    }

    private NodoMVias<K,V> subNodo(NodoMVias<K,V> nodoActual, int desde, int hasta) {
        NodoMVias<K,V> nuevoNodo = new NodoMVias<>(orden);
        int c = 0;
        for (int i = desde; i < hasta; i++) {
            nuevoNodo.setClave(c, nodoActual.getClave(i));
            nuevoNodo.setHijo(c, nodoActual.getHijo(i));
            c++;
        }
        nuevoNodo.setHijo(c, nodoActual.getHijo(hasta));
        return nuevoNodo;
    }

    private void dividir(NodoMVias<K,V> nodoActual, Stack<NodoMVias<K,V>> pilaDeAncestros) {
        int posQueSube = this.nroMinDatos;
        K datoQueSube = nodoActual.getClave(posQueSube);
        NodoMVias<K,V> nodoPadre = pilaDeAncestros.isEmpty() ? new NodoMVias<>(orden) : pilaDeAncestros.pop();
        NodoMVias<K,V> nodoHijoIzq = this.subNodo(nodoActual, 0, posQueSube);
        NodoMVias<K,V> nodoHijoDer = this.subNodo(nodoActual, posQueSube + 1, orden - 1);
        int pos = super.posicionPorDondeBajar(nodoPadre, datoQueSube);
        super.insertarDatoEnNodo(nodoPadre, datoQueSube);
        this.recorrerHijosDivision(nodoPadre, pos);
        nodoPadre.setHijo(pos, nodoHijoIzq);
        nodoPadre.setHijo(pos + 1, nodoHijoDer);
        if (pilaDeAncestros.isEmpty()) {
            raiz = nodoPadre;
        }
        if (nodoPadre.cantidadDeClavesNoVacias() > this.nroMaxDatos) {
            this.dividir(nodoPadre, pilaDeAncestros);
        }
    }

    private void recorrerHijosDivision(NodoMVias<K,V> nodoActual, int pos) {
        for (int i = nodoActual.cantidadDeClavesNoVacias(); i > pos; i--) {
            nodoActual.setHijo(i, nodoActual.getHijo(i - 1));
        }
    }

    private boolean datoEnNodo(NodoMVias<K,V> nodoActual, K dato) {
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            if (dato.compareTo(nodoActual.getClave(i)) == 0) {
                return true;
            }
        }
        return false;
    }

@Override
    public V eliminar(K claveAEliminar) {
        Stack<NodoMVias<K, V>> pilaDeAncestros = new Stack<>();
        NodoMVias<K, V> nodoActual = raiz;
        if (NodoMVias.esNodoVacio(nodoActual)) {
            throw new IllegalArgumentException("no puede ser nodo vacio");
        }
        if (!super.contiene(claveAEliminar)) {
            throw new IllegalArgumentException("no se encuentra la clave");
        }
        while (!NodoMVias.esNodoVacio(nodoActual)) {
            int posicionDelDato = super.posicionPorDondeBajar(nodoActual, claveAEliminar) - 1;
            if (nodoActual.esHoja()) {
                //  T datoSucesor = super.sucesorInOrden(raiz, datoAEliminar);
                eliminarDatoDeNodo(nodoActual, posicionDelDato);
                if (nodoActual.cantidadDeClavesNoVacias() < this.nroMinDatos) {
                    if (pilaDeAncestros.isEmpty()) {
                        if (nodoActual.cantidadDeClavesNoVacias() == 0) { //nodoActual es la raiz
                            super.vaciar();
                        }
                    } else {
                        this.prestarseOFusionarse(nodoActual, pilaDeAncestros);
                    }
                }
                return nodoActual.getValor(posicionDelDato);
            } else {
                if (this.datoEnNodo(nodoActual, claveAEliminar)) {
                    K datoPredecesor = predecesorInOrden(nodoActual, claveAEliminar);
                    NodoMVias<K, V> nodoDelPredecesor = buscarNodoDelPredecesor(nodoActual,
                            datoPredecesor, pilaDeAncestros);
                    int posDelPredecesor = nodoDelPredecesor.cantidadDeClavesNoVacias() - 1;
                    nodoActual.setClave(posicionDelDato, datoPredecesor);
                    eliminarDatoDeNodo(nodoDelPredecesor, posDelPredecesor);
                    if (nodoDelPredecesor.cantidadDeClavesNoVacias() < this.nroMinDatos) {
                        this.prestarseOFusionarse(nodoDelPredecesor, pilaDeAncestros);
                    }
                    nodoActual = NodoMVias.nodoVacio();
                } else {
                    int pos = super.posicionPorDondeBajar(nodoActual, claveAEliminar);
                    pilaDeAncestros.push(nodoActual);
                    nodoActual = nodoActual.getHijo(pos);
                }
            }
        }
        return (V)NodoMVias.datoVacio();
    }
 public List<K> recorridoEnInOrdenRec() {
        List<K> recorrido = new ArrayList<>();
        recorridoEnInOrdenRec(this.raiz,recorrido);
        return recorrido ;
        
    }
    private void recorridoEnInOrdenRec(NodoMVias<K,V> nodoActual,List<K> recorrido){
        if (NodoMVias.esNodoVacio(nodoActual)){
            return;
        }
        
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias();i++){
            recorrido.add(nodoActual.getClave(i));
            recorridoEnInOrdenRec(nodoActual.getHijo(i),recorrido);
        }
        
        recorridoEnPreOrdenRec(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias()),recorrido);
    }
        protected K predecesorInOrden(NodoMVias<K, V> nodoActual, K clave) {
        if (this.contiene(clave)) {
            List<K> datos = new LinkedList<>();
            this.recorridoEnInOrdenRec(nodoActual, datos);
            int ind = indiceDe(datos, clave);
            return datos.get(ind - 1);
        }
        return (K) NodoMVias.datoVacio();
    }
            private int indiceDe(List<K> claves, K clave) {
        for (int i = 0; i < claves.size(); i++) {
            K datoActual = claves.get(i);
            if (clave.compareTo(datoActual) == 0) {
                return i;
            }
        }
        return -1;
    }
    private int posicionHijoNulo(NodoMVias<K, V> nodoActual, int cant) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return -1;
        }
        for (int i = 0; i <= cant; i++) {
            NodoMVias<K, V> nodoHijo = nodoActual.getHijo(i);
            if (nodoHijo.cantidadDeClavesNoVacias() < this.nroMinDatos) {
                return i;
            }
        }
        return -1;
    }

    private int indiceDeFusion(NodoMVias<K, V> nodoPadre, int posNodoHijo) {
        int cant = nodoPadre.cantidadDeClavesNoVacias();
        NodoMVias<K, V> nodoHermanoDer = posNodoHijo != cant ? nodoPadre.getHijo(posNodoHijo + 1)
                : NodoMVias.nodoVacio();
        return !NodoMVias.esNodoVacio(nodoHermanoDer) ? 1 : -1;
    }

    private int indiceDePrestamo(NodoMVias<K, V> nodoPadre, int posNodoHijo) {
        int cant = nodoPadre.cantidadDeClavesNoVacias();
        NodoMVias<K, V> nodoHermanoDer = posNodoHijo != cant ? nodoPadre.getHijo(posNodoHijo + 1)
                : NodoMVias.nodoVacio();
        NodoMVias<K, V> nodoHermanoIzq = posNodoHijo > 0 ? nodoPadre.getHijo(posNodoHijo - 1)
                : NodoMVias.nodoVacio();
        if (!NodoMVias.esNodoVacio(nodoHermanoDer)
                && nodoHermanoDer.cantidadDeClavesNoVacias() > this.nroMinDatos) {
            return 1;
        } else if (!NodoMVias.esNodoVacio(nodoHermanoIzq)
                && nodoHermanoIzq.cantidadDeClavesNoVacias() > this.nroMinDatos) {
            return -1;
        }
        return 0;
    }

    private NodoMVias<K, V> buscarNodoDelPredecesor(NodoMVias<K,V> nodoActual, K dato,
            Stack<NodoMVias<K, V>> pilaDeAncestros) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return pilaDeAncestros.pop();
        }
        int pos = super.posicionPorDondeBajar(nodoActual, dato);
        pilaDeAncestros.push(nodoActual);
        return this.buscarNodoDelPredecesor(nodoActual.getHijo(pos), dato, pilaDeAncestros);
    }

    private void prestarseOFusionarse(NodoMVias<K, V> nodoActual, Stack<NodoMVias<K, V>> pilaDeAncestros) {
        NodoMVias<K, V> nodoPadre = pilaDeAncestros.peek();
        int posNodoEliminado = this.posicionHijoNulo(nodoPadre, nodoPadre.cantidadDeClavesNoVacias());
        int indicePrestamo = this.indiceDePrestamo(nodoPadre, posNodoEliminado);
        if (indicePrestamo != 0 && nodoActual.esHoja()) {
            this.prestamoDato(nodoActual, indicePrestamo, pilaDeAncestros);
        } else {
            int indFusion = this.indiceDeFusion(nodoPadre, posNodoEliminado);
            if (indFusion == 1) {
                this.fusionPorDerecha(nodoActual, pilaDeAncestros);
            } else {
                this.fusionPorIzquierda(nodoActual, pilaDeAncestros);
            }
        }
    }

    private void prestamoDato(NodoMVias<K, V> nodoActual, int indicePrestamo,
            Stack<NodoMVias<K, V>> pilaDeAncestros) {
        if (indicePrestamo == 1) {
            System.out.println("Prestamo por der");
        } else {
            System.out.println("Prestamo por izq");
        }
        NodoMVias<K, V> nodoPadre = pilaDeAncestros.pop();
        int posNodoEliminado = this.posicionHijoNulo(nodoPadre, nodoPadre.cantidadDeClavesNoVacias());
        //int cant = nodoPadre.cantidadDeDatosNoVacios();
        NodoMVias<K, V> nodoHermano = nodoPadre.getHijo(posNodoEliminado + indicePrestamo);
        int posDatoPadre = indicePrestamo > 0 ? posNodoEliminado
                : posNodoEliminado - 1;
        K datoPadre = nodoPadre.getClave(posDatoPadre);
        int posDatoHermano = indicePrestamo > 0 ? 0 : nodoHermano.cantidadDeClavesNoVacias() - 1;
        K datoHermano = nodoHermano.getClave(posDatoHermano);
        eliminarDatoDeNodo(nodoPadre, posDatoPadre);
        eliminarDatoDeNodo(nodoHermano, posDatoHermano);
        super.insertarDatoEnNodo(nodoActual, datoPadre);
        super.insertarDatoEnNodo(nodoPadre, datoHermano);
    }
    protected void eliminarDatoDeNodo(NodoMVias<K, V> nodoActual, int i) {
        int cant = nodoActual.cantidadDeClavesNoVacias();
        for (int k = i; k < cant - 1; k++) {
            K datoNuevo = nodoActual.getClave(k + 1);
            nodoActual.setClave(k, datoNuevo);
        }
        nodoActual.setClave(cant - 1, (K) NodoMVias.datoVacio());
    }
    private void eliminarHijo(NodoMVias<K, V> nodoPadre, int posicion, int cantHijos) {
        for (int i = posicion; i < cantHijos; i++) {
            nodoPadre.setHijo(i, nodoPadre.getHijo(i + 1));
        }
    }

    private void insertarHijosFusion(NodoMVias<K, V> nodoAInsertar, NodoMVias<K, V> nodoDeLosHijos,
            int posAInsertar, int posPrimerHijo) {
        int cant2 = nodoDeLosHijos.cantidadDeHijosNoVacios();
        int cont = posAInsertar;
        for (int i = posPrimerHijo; i < cant2; i++) {
            nodoAInsertar.setHijo(cont, nodoDeLosHijos.getHijo(i));
            cont++;
        }
    }

    private void insertarDatosFusion(NodoMVias<K, V> nodoActual, NodoMVias<K, V> nodoHermano) {
        for (int i = 0; i < nodoHermano.cantidadDeClavesNoVacias(); i++) {
            super.insertarDatoEnNodo(nodoActual, nodoHermano.getClave(i));
        }
    }

    private void fusionPorDerecha(NodoMVias<K, V> nodoActual, Stack<NodoMVias<K, V>> pilaDeAncestros) {
        NodoMVias<K, V> nodoPadre = pilaDeAncestros.pop();
        int cantDatosPadre = nodoPadre.cantidadDeClavesNoVacias();
        int cantHijosPadre = cantDatosPadre + 1;
        int posNodoNulo = this.posicionHijoNulo(nodoPadre, cantDatosPadre);
        K datoPadre = nodoPadre.getClave(posNodoNulo);
        NodoMVias<K, V> nodoHermano = nodoPadre.getHijo(posNodoNulo + 1);
        K datoHermano = nodoHermano.getClave(0);
        NodoMVias<K, V> nodoPredecesorPadre = nodoHermano.getHijo(0);
        super.insertarDatoEnNodo(nodoActual, datoPadre);
        super.insertarDatoEnNodo(nodoActual, datoHermano);
        eliminarDatoDeNodo(nodoPadre, posNodoNulo);
        eliminarDatoDeNodo(nodoHermano, 0);
        if (nodoActual.esHoja()) {
            this.eliminarHijo(nodoPadre, posNodoNulo + 1, cantHijosPadre);
        } else {
            nodoActual.setHijo(1, nodoPredecesorPadre);
            this.insertarDatosFusion(nodoActual, nodoHermano);
            this.insertarHijosFusion(nodoActual, nodoHermano, 2, 1);
            this.eliminarHijo(nodoPadre, posNodoNulo + 1, cantHijosPadre);
        }
        if (nodoPadre.cantidadDeClavesNoVacias() == 0) {
            if (nodoPadre == raiz) {
                raiz = nodoActual;
                nodoPadre = nodoActual;
            }
        }
        if (nodoPadre.cantidadDeClavesNoVacias() < this.nroMinDatos) {
            this.prestarseOFusionarse(nodoPadre, pilaDeAncestros);
        } else if (nodoActual.cantidadDeClavesNoVacias() > this.nroMaxDatos) {
            pilaDeAncestros.push(nodoPadre);
            this.dividir(nodoActual, pilaDeAncestros);
        }

    }

    private void fusionPorIzquierda(NodoMVias<K, V> nodoActual, Stack<NodoMVias<K, V>> pilaDeAncestros) {
        NodoMVias<K, V> nodoPadre = pilaDeAncestros.pop();
        int cantDatosPadre = nodoPadre.cantidadDeClavesNoVacias();
        int cantHijosPadre = cantDatosPadre + 1;
        int posNodoNulo = this.posicionHijoNulo(nodoPadre, cantDatosPadre);
        K datoPadre = nodoPadre.getClave(cantDatosPadre - 1);
        NodoMVias<K, V> nodoHermano = nodoPadre.getHijo(posNodoNulo - 1);
        super.insertarDatoEnNodo(nodoHermano, datoPadre);
        eliminarDatoDeNodo(nodoPadre, cantDatosPadre - 1);
        if (nodoActual.esHoja()) {
            this.eliminarHijo(nodoPadre, posNodoNulo, cantHijosPadre);
        } else {
            this.insertarHijosFusion(nodoHermano, nodoActual, nodoHermano.cantidadDeClavesNoVacias(),
                    nodoActual.cantidadDeClavesNoVacias());
        }
        if (nodoPadre.cantidadDeClavesNoVacias() == 0) {
            if (nodoPadre == raiz) {
                raiz = nodoHermano;
                nodoPadre = nodoHermano;
            }
        }
        if (nodoPadre.cantidadDeClavesNoVacias() < this.nroMinDatos) {
            this.prestarseOFusionarse(nodoPadre, pilaDeAncestros);
        }
    }
    public List<K> recorridoEnPreOrdenRec() {
        List<K> recorrido = new ArrayList<>();
        recorridoEnPreOrdenRec(this.raiz,recorrido);
        return recorrido ;
        
    }
    private void recorridoEnPreOrdenRec(NodoMVias<K,V> nodoActual,List<K> recorrido){
        if (NodoMVias.esNodoVacio(nodoActual)){
            return;
        }
        
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias();i++){
            recorridoEnPreOrdenRec(nodoActual.getHijo(i),recorrido);
            recorrido.add(nodoActual.getClave(i));
        }
        
        recorridoEnPreOrdenRec(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias()),recorrido);
    }
}
