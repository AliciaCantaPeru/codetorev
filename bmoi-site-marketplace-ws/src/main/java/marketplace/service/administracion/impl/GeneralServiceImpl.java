/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import marketplace.repository.CategoriasGrupomenuRepository;
import marketplace.repository.DepartamentosRepository;
import marketplace.repository.DigImgdisenioGruposRepository;
import marketplace.repository.DigImgdisenioSubcategoriasImgRepository;
import marketplace.repository.DigImgdisenioSubcategoriasRepository;
import marketplace.repository.DigTiposPersonalizacionRepository;
import marketplace.repository.DistritosRepository;
import marketplace.repository.MenusRepository;
import marketplace.repository.PaisesRepository;
import marketplace.repository.PersonasRepository;
import marketplace.repository.PostulantesRepository;
import marketplace.repository.ProductoBrandsRepository;
import marketplace.repository.ProductoColoresRepository;
import marketplace.repository.ProductoTallasRepository;
import marketplace.repository.ProductosRepository;
import marketplace.repository.ProvinciasRepository;
import marketplace.repository.SellerPersonasRepository;
import marketplace.repository.TblmasterOpcionesRepository;
import marketplace.repository.TblmasterParametrosRepository;
import marketplace.repository.ValorCambioRepository;
import marketplace.repository.entity.Departamentos;
import marketplace.repository.entity.DigImgdisenioGrupos;
import marketplace.repository.entity.DigImgdisenioSubcategoriasImg;
import marketplace.repository.entity.DigTiposPersonalizacion;
import marketplace.repository.entity.Distritos;
import marketplace.repository.entity.Menus;
import marketplace.repository.entity.Paises;
import marketplace.repository.entity.Postulantes;
import marketplace.repository.entity.ProductoBrands;
import marketplace.repository.entity.ProductoColores;
import marketplace.repository.entity.ProductoTallas;
import marketplace.repository.entity.Productos;
import marketplace.repository.entity.Provincias;
import marketplace.repository.entity.QDepartamentos;
import marketplace.repository.entity.QDistritos;
import marketplace.repository.entity.QProductos;
import marketplace.repository.entity.QProvincias;
import marketplace.repository.entity.QSellerPersonas;
import marketplace.repository.entity.TblmasterOpciones;
import marketplace.repository.entity.TblmasterParametros;
import marketplace.repository.entity.ValorCambio;
import marketplace.repository.entity.categoriasgrupomenu.CategoriasGrupomenuEntity;
import marketplace.repository.entity.figimgdiseniosubcategorias.GrupoCategoriaDisenioEntity;
import marketplace.service.administracion.GeneralService;
import marketplace.service.administracion.dto.general.GeneralMapper;
import marketplace.service.administracion.dto.general.ImagenDto;
import marketplace.service.administracion.dto.general.MenuGrupoCategoriaDto;
import marketplace.service.administracion.dto.general.OpcionDto;
import marketplace.service.administracion.dto.general.ParametroDto;
import marketplace.service.administracion.dto.general.TipoCambioDto;
import marketplace.support.dto.Opcion;
import marketplace.util.TipoUsuarioLogin;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PCADMIN
 */
@Service
public class GeneralServiceImpl implements GeneralService {

    @Autowired
    private TblmasterParametrosRepository parametrosRepository;
    @Autowired
    private PaisesRepository paisesRepository;
    @Autowired
    private DepartamentosRepository departamentosRepository;
    @Autowired
    private ProvinciasRepository provinciasRepository;
    @Autowired
    private DistritosRepository distritosRepository;
    @Autowired
    private GeneralMapper generalMapper;
    @Autowired
    private SellerPersonasRepository sellerPersonasRepository;
    @Autowired
    private PersonasRepository personasRepository;
    @Autowired
    private TblmasterOpcionesRepository opcionesRepository;
    @Autowired
    private ValorCambioRepository cambioRepository;
    @Autowired
    private CategoriasGrupomenuRepository categoriasGrupomenuRepository;
    @Autowired
    private MenusRepository menusRepository;
    @Autowired
    private ProductoBrandsRepository brandsRepository;
    @Autowired
    private ProductoColoresRepository coloresRepository;
    @Autowired
    private ProductoTallasRepository productoTallasRepository;
    @Autowired
    private DigTiposPersonalizacionRepository personalizacionRepository;
    @Autowired
    private DigImgdisenioGruposRepository digImgdisenioMenusRepository;
    @Autowired
    private ProductosRepository productosRepository;
    @Autowired
    private DigImgdisenioSubcategoriasRepository digImgdisenioSubcategoriasRepository;
    @Autowired
    private DigImgdisenioSubcategoriasImgRepository subcategoriasImgRepository;
    @Autowired
    private PostulantesRepository postulantesRepository;

    @Override
    public List<ParametroDto> listarParametro() throws Exception {
        Iterable<TblmasterParametros> listaParamerosEntity = parametrosRepository.findAll();
        List<ParametroDto> listaOut = generalMapper.toParametroDtos(listaParamerosEntity);
        return listaOut;
    }

    @Override
    public List<Opcion> listarPais() throws Exception {
        Iterable<Paises> listaPais = paisesRepository.findAll();
        return generalMapper.toUbigeoOutPaises(listaPais);
    }

    @Override
    public List<Opcion> listarDepartamento(int idPais) throws Exception {
        Predicate p = QDepartamentos.departamentos.idpais.id.eq(idPais);
        Iterable<Departamentos> listaDepartamentos = departamentosRepository.findAll(p);
        return generalMapper.toUbigeoOutDepartamentos(listaDepartamentos);
    }

    @Override
    public List<Opcion> listarProvincia(int idDepartamento) throws Exception {
        Predicate p = QProvincias.provincias.iddepartamento.id.eq(idDepartamento);
        Iterable<Provincias> listaProvincia = provinciasRepository.findAll(p);
        return generalMapper.toUbigeoOutProvincias(listaProvincia);
    }

    @Override
    public List<Opcion> listarDistrito(int idProvincia) throws Exception {
        Predicate p = QDistritos.distritos.idprovincia.id.eq(idProvincia);
        Iterable<Distritos> listaDistrito = distritosRepository.findAll(p);
        return generalMapper.toUbigeoOutDistritos(listaDistrito);
    }

    @Override
    public boolean buscarRepeticionCorreo(String correo, int idPersona, String tipoUsuario) throws Exception {
        BooleanBuilder builder = new BooleanBuilder();
        if (tipoUsuario.equals(TipoUsuarioLogin.BMOI.getValor())) {
            builder.and(QSellerPersonas.sellerPersonas.idseller.id.eq(1));
        }
        if (tipoUsuario.equals(TipoUsuarioLogin.SELLER.getValor())) {
            builder.and(QSellerPersonas.sellerPersonas.idseller.id.ne(1));
        }
        if (idPersona != 0) {
            builder.and(QSellerPersonas.sellerPersonas.email.toUpperCase().eq(correo.toUpperCase().trim())).and(QSellerPersonas.sellerPersonas.id.ne(idPersona));
        } else {
            builder.and(QSellerPersonas.sellerPersonas.email.toUpperCase().eq(correo.toUpperCase().trim()));
        }
        return sellerPersonasRepository.exists(builder);
    }

    @Override
    public List<OpcionDto> listarOpciones() throws Exception {
        Iterable<TblmasterOpciones> listaOpcion = opcionesRepository.findAll();
        List<OpcionDto> listaOut = generalMapper.toOpcionDtos(listaOpcion);
        return listaOut;
    }

    @Override
    public List<TipoCambioDto> listarTipoCambio() throws Exception {
//        QValorCambio val = new QValorCambio("val");
//        Predicate p = QValorCambio.valorCambio.id.eq(JPAExpressions.select(val.id.max()).from(val));
        Iterable<ValorCambio> listaValorCambio = cambioRepository.findAll();
        return generalMapper.toTipoCambioDtos(listaValorCambio);
    }

    @Override
    public List<MenuGrupoCategoriaDto> listarMenuGrupoCategoria(String codigoMenu) throws Exception {
        List<CategoriasGrupomenuEntity> categoriasGrupomenuEntitys = categoriasGrupomenuRepository.listarGrupoCategoria(codigoMenu);
        Map<Pair<Integer, String>, List<CategoriasGrupomenuEntity>> listaGrupo = categoriasGrupomenuEntitys
                .stream().collect(Collectors.groupingBy(cat -> Pair.of(cat.getIdGrupo(), cat.getGrupo())));
        List<MenuGrupoCategoriaDto> listaGruposCategorias = new ArrayList<>();
        for (Map.Entry<Pair<Integer, String>, List<CategoriasGrupomenuEntity>> grupo : listaGrupo.entrySet()) {
            Pair<Integer, String> keyGrupo = grupo.getKey();
            List<CategoriasGrupomenuEntity> grupoCategorias = grupo.getValue();
            MenuGrupoCategoriaDto grupoDto = new MenuGrupoCategoriaDto();
            grupoDto.setId(keyGrupo.getKey());
            grupoDto.setNombre(keyGrupo.getValue());
            grupoDto.setListaHijos(new ArrayList<>());
            listaGruposCategorias.add(grupoDto);
            for (CategoriasGrupomenuEntity grupoCategoria : grupoCategorias) {
                MenuGrupoCategoriaDto categoriaDto = new MenuGrupoCategoriaDto();
                categoriaDto.setId(grupoCategoria.getIdCategoriaGrupoMenu());
                categoriaDto.setNombre(grupoCategoria.getCategoria());
                grupoDto.getListaHijos().add(categoriaDto);
            }
        }
        return listaGruposCategorias;
    }

    @Override
    public List<MenuGrupoCategoriaDto> listarCategoriaDisenio(int idGrupoDisenio) throws Exception {
        List<GrupoCategoriaDisenioEntity> categoriasGrupomenuEntitys = digImgdisenioSubcategoriasRepository.listarGrupoCategoriaDisenio(idGrupoDisenio);
        Map<Pair<Integer, String>, List<GrupoCategoriaDisenioEntity>> listaGrupo = categoriasGrupomenuEntitys
                .stream().collect(Collectors.groupingBy(cat -> Pair.of(cat.getIdGrupo(), cat.getGrupo())));
        List<MenuGrupoCategoriaDto> listaGruposCategorias = new ArrayList<>();
        for (Map.Entry<Pair<Integer, String>, List<GrupoCategoriaDisenioEntity>> grupoEntity : listaGrupo.entrySet()) {
            Pair<Integer, String> keyGrupo = grupoEntity.getKey();
            List<GrupoCategoriaDisenioEntity> listaCategoriaEntity = grupoEntity.getValue();
            Map<Pair<Integer, String>, List<GrupoCategoriaDisenioEntity>> listaCategoria = listaCategoriaEntity
                    .stream().collect(Collectors.groupingBy(cat -> Pair.of(cat.getIdCategoria(), cat.getCategoria())));
            MenuGrupoCategoriaDto grupoDto = new MenuGrupoCategoriaDto();
            grupoDto.setId(keyGrupo.getKey());
            grupoDto.setNombre(keyGrupo.getValue());
            grupoDto.setListaHijos(new ArrayList<>());
            for (Map.Entry<Pair<Integer, String>, List<GrupoCategoriaDisenioEntity>> grupo : listaCategoria.entrySet()) {
                Pair<Integer, String> keyCategoria = grupo.getKey();
                List<GrupoCategoriaDisenioEntity> listaSubcategoria = grupo.getValue();
                MenuGrupoCategoriaDto catDto = new MenuGrupoCategoriaDto();
                catDto.setId(keyCategoria.getKey());
                catDto.setNombre(keyCategoria.getValue());
                catDto.setListaHijos(new ArrayList<>());
                grupoDto.getListaHijos().add(catDto);
                for (GrupoCategoriaDisenioEntity grupoCategoria : listaSubcategoria) {
                    MenuGrupoCategoriaDto categoriaDto = new MenuGrupoCategoriaDto();
                    categoriaDto.setId(grupoCategoria.getIdSubcategoria());
                    categoriaDto.setNombre(grupoCategoria.getSubcategoria());
                    catDto.getListaHijos().add(categoriaDto);
                }
            }
            listaGruposCategorias.add(grupoDto);
        }
        return listaGruposCategorias;
    }

    @Override
    public List<Opcion> listarMenuOpcion() throws Exception {
        Iterable<Menus> listaMenus = menusRepository.findAll();
        List<Opcion> listaOpcion = new ArrayList();
        for (Menus menu : listaMenus) {
            Opcion opcion = new Opcion();
            opcion.setNombre(menu.getNombre());
            opcion.setCodigo(menu.getCodigo());
            listaOpcion.add(opcion);
        }
        return listaOpcion;
    }

    @Override
    public List<Opcion> listarMarca() throws Exception {
        Iterable<ProductoBrands> listaBrands = brandsRepository.findAll();
        List<Opcion> listaOpcion = new ArrayList();
        for (ProductoBrands menu : listaBrands) {
            Opcion opcion = new Opcion();
            opcion.setId(menu.getId());
            opcion.setNombre(menu.getNombre());
            listaOpcion.add(opcion);
        }
        return listaOpcion;
    }

    @Override
    public List<Opcion> listarColores() throws Exception {
        Iterable<ProductoColores> listaBrands = coloresRepository.findAll();
        List<Opcion> listaOpcion = new ArrayList();
        for (ProductoColores menu : listaBrands) {
            Opcion opcion = new Opcion();
            opcion.setId(menu.getId());
            opcion.setNombre(menu.getNombre());
            opcion.setCodigo(menu.getCodigo());
            listaOpcion.add(opcion);
        }
        return listaOpcion;
    }

    @Override
    public List<Opcion> listarTallas() throws Exception {
        Iterable<ProductoTallas> listaBrands = productoTallasRepository.findAll();
        List<Opcion> listaOpcion = new ArrayList();
        for (ProductoTallas menu : listaBrands) {
            Opcion opcion = new Opcion();
            opcion.setId(menu.getId());
            opcion.setCodigo(menu.getCodigo());
            opcion.setNombre(menu.getNombre());

            listaOpcion.add(opcion);
        }
        return listaOpcion;
    }

    @Override
    public List<Opcion> listarTipoPersonalizacion() throws Exception {
        Iterable<DigTiposPersonalizacion> listaBrands = personalizacionRepository.findAll();
        List<Opcion> listaOpcion = new ArrayList();
        for (DigTiposPersonalizacion menu : listaBrands) {
            Opcion opcion = new Opcion();
            opcion.setId(menu.getId());
            opcion.setNombre(menu.getNombre());
            opcion.setDescripcion(menu.getDescripcion());
            listaOpcion.add(opcion);
        }
        return listaOpcion;
    }

    @Override
    public List<Opcion> listarMenuDisenio() throws Exception {
        Iterable<DigImgdisenioGrupos> listaBrands = digImgdisenioMenusRepository.findAll();
        List<Opcion> listaOpcion = new ArrayList();
        for (DigImgdisenioGrupos menu : listaBrands) {
            Opcion opcion = new Opcion();
            opcion.setId(menu.getId());
            opcion.setNombre(menu.getNombre());
            listaOpcion.add(opcion);
        }
        return listaOpcion;
    }

    @Override
    public List<Opcion> listarProductoPadre() throws Exception {
        Predicate p = QProductos.productos.idPrdpadre.isNull();
        Iterable<Productos> listaProductos = productosRepository.findAll(p);
        List<Opcion> listaOpcion = new ArrayList();
        for (Productos menu : listaProductos) {
            Opcion opcion = new Opcion();
            opcion.setImg(menu.getMultimedia1());
            opcion.setNombre(menu.getNombrecorto());
            opcion.setCodigo(menu.getSku());
            listaOpcion.add(opcion);
        }
        return listaOpcion;
    }

    @Override
    public List<Opcion> listarParametros() throws Exception {
        Predicate p = QProductos.productos.idPrdpadre.isNull();
        Iterable<Productos> listaProductos = productosRepository.findAll(p);
        List<Opcion> listaOpcion = new ArrayList();
        for (Productos menu : listaProductos) {
            Opcion opcion = new Opcion();
            opcion.setImg(menu.getMultimedia1());
            opcion.setNombre(menu.getNombrecorto());
            opcion.setCodigo(menu.getSku());
            listaOpcion.add(opcion);
        }
        return listaOpcion;
    }

    @Override
    public List<ImagenDto> listarImagen() throws Exception {
        Iterable<DigImgdisenioSubcategoriasImg> listaBd = subcategoriasImgRepository.findAll();
        List<ImagenDto> listaDto = new ArrayList<>();
        for (DigImgdisenioSubcategoriasImg img : listaBd) {
            ImagenDto imgDto = new ImagenDto();
            imgDto.setIdDigImgSubImg(img.getId());
            imgDto.setUrl(img.getUrl());
            imgDto.setIdSubcategoria(img.getIdDigImgSub().getId());
            listaDto.add(imgDto);
        }
        return listaDto;
    }

    @Override
    public List<Opcion> listarPostulante() throws Exception {
        Iterable<Postulantes> listaProductos = postulantesRepository.findAll();
        List<Opcion> listaOpcion = new ArrayList();
        for (Postulantes menu : listaProductos) {
            Opcion opcion = new Opcion();
            opcion.setNombre(menu.getRazonSocial());
            opcion.setId(menu.getId());
            listaOpcion.add(opcion);
        }
        return listaOpcion;
    }

}
