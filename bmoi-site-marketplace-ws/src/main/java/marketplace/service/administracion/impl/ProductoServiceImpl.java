/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.impl;

import com.google.common.collect.Lists;
import com.querydsl.core.types.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import marketplace.logger.ExceptionUser;
import marketplace.repository.CategoriasGrupomenuRepository;
import marketplace.repository.DigImgdisenioSubcategoriasRepository;
import marketplace.repository.DigProductoTipopersonalizacionRepository;
import marketplace.repository.ProductoAssetsMultimediaRepository;
import marketplace.repository.ProductoCatgrupmenuRepository;
import marketplace.repository.ProductoDimensionesRepository;
import marketplace.repository.ProductoImagenPropioRepository;
import marketplace.repository.ProductoImagenRepository;
import marketplace.repository.ProductoVariantesRepository;
import marketplace.repository.ProductosRepository;
import marketplace.repository.SellerCuentasRepository;
import marketplace.repository.entity.CategoriasGrupomenu;
import marketplace.repository.entity.DigProductoTipopersonalizacion;
import marketplace.repository.entity.ProductoAssetsMultimedia;
import marketplace.repository.entity.ProductoCatgrupmenu;
import marketplace.repository.entity.ProductoDimensiones;
import marketplace.repository.entity.ProductoImagen;
import marketplace.repository.entity.ProductoImagenPropio;
import marketplace.repository.entity.ProductoVariantes;
import marketplace.repository.entity.Productos;
import marketplace.repository.entity.QSellerCuentas;
import marketplace.repository.entity.SellerCuentas;
import marketplace.repository.entity.figimgdiseniosubcategorias.GrupoCategoriaDisenioEntity;
import marketplace.service.administracion.ProductoService;
import marketplace.service.administracion.dto.general.MenuGrupoCategoriaDto;
import marketplace.service.administracion.dto.producto.ProductoInDto;
import marketplace.service.administracion.dto.producto.ProductoMantDto;
import marketplace.service.administracion.dto.producto.ProductoMapper;
import marketplace.service.administracion.dto.producto.ProductoOutDto;
import marketplace.util.Util;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductosRepository productosRepository;
    @Autowired
    private ProductoMapper productoMapper;
    @Autowired
    private SellerCuentasRepository sellerCuentasRepository;
    @Autowired
    private ProductoAssetsMultimediaRepository assetsMultimediaRepository;
    @Autowired
    private ProductoImagenRepository imgdisenioRepository;
    @Autowired
    private ProductoImagenPropioRepository imagenPropioRepository;
    @Autowired
    private DigProductoTipopersonalizacionRepository digProductoTipopersonalizacionRepository;
    @Autowired
    private ProductoDimensionesRepository dimensionesRepository;
    @Autowired
    private ProductoVariantesRepository productoVariantesRepository;
    @Autowired
    private ProductoCatgrupmenuRepository productoCatgrupmenuRepository;
    @Autowired
    private DigImgdisenioSubcategoriasRepository digImgdisenioSubcategoriasRepository;
    @Autowired
    private CategoriasGrupomenuRepository categoriasGrupomenuRepository;

    @Override
    public PageImpl<ProductoOutDto> listar(ProductoInDto in) throws Exception {
        PageRequest pageRequest = PageRequest.of(in.getPagina(), in.getCantidad());
        List<ProductoOutDto> listaclienteOut;
        Predicate p = QSellerCuentas.sellerCuentas.idSellerpersona.id.eq(in.getIdPersonaLogeada());
        Optional<SellerCuentas> optionalCuenta = sellerCuentasRepository.findOne(p);
//        Integer idSeller = null;
//        if (optionalCuenta.isPresent()) {
        SellerCuentas cuentaEntity = optionalCuenta.get();
//            boolean esMaster = Rol.esMaster(cuentaEntity.getIdRol().getId());
//            idSeller = esMaster ? null : cuentaEntity.getIdSellerpersona().getIdseller().getId();
//        }
        Integer idSeller = cuentaEntity.getIdSellerpersona().getIdseller().getId();
        PageImpl<Productos> pageEntity = productosRepository.listarProducto(in.getSortCampo(), in.getSortOrden(), in.getTexto(), pageRequest, idSeller, in.getSeccion());
        listaclienteOut = productoMapper.toProductoMantDtos(pageEntity.toList());
        return new PageImpl<>(listaclienteOut, pageRequest, pageEntity.getTotalElements());
    }

    @Override
    public ProductoMantDto obtener(int ipPersonaLogeada, int id) throws Exception {
        sellerCuentasRepository.validaObtenerCorreo(ipPersonaLogeada);
        Optional<Productos> OptNegocio = productosRepository.findById(id);
        if (!OptNegocio.isPresent()) {
            throw new ExceptionUser("El producto no esta registrado");
        }
        Productos producto = OptNegocio.get();
        ProductoMantDto mantDto = productoMapper.toProductoMantDto(producto);
        mantDto.setIdUsuarioLogeado(ipPersonaLogeada);
        List<MenuGrupoCategoriaDto> listaGrupoCategoria = listarCategoriaDisenio(id);
        List<ProductoMantDto.ProductoAssetsMultimediaDto> listaMultimedia = producto.getProductoAssetsMultimediaSet().stream().map(asset -> productoMapper.toMultimediaAssetsDto(asset)).collect(Collectors.toList());
        List<ProductoMantDto.ProductoVariantesDto> listaVariante = producto.getProductoVariantesSet().stream().map(pro -> productoMapper.toProductoVarianteDto(pro)).collect(Collectors.toList());
        ProductoMantDto.ProductoDimensionesDto dimension = producto.getProductoDimensionesSet().stream().map(pro -> productoMapper.toProductoDimensionDto(pro)).findFirst().get();
        List<ProductoMantDto.DigProductoTipopersonalizacionDto> listaPersonalizacion = producto.getDigProductoTipopersonalizacionSet().stream().map(pro -> productoMapper.toDigProductoTipopersonalizacionDto(pro)).collect(Collectors.toList());
        List<ProductoMantDto.ProductoImagenDto> listaImgDisenio = producto.getProductoImagenSet().stream().map(pro -> productoMapper.toProductoImagenDto(pro)).collect(Collectors.toList());
        List<ProductoMantDto.ProductoImagenPropioDto> listaImgDisenioPropio = producto.getProductoImagenPropioSet().stream().map(pro -> productoMapper.toProductoImagenPropioDto(pro)).collect(Collectors.toList());
        List<ProductoMantDto.ProductoCategoriaDto> listaCategoria = producto.getProductoCatgrupmenuSet().stream().map(pro -> productoMapper.toProductoCategoriaDto(pro)).collect(Collectors.toList());
        mantDto.setDimension(dimension);
        mantDto.setListaMultimedia(listaMultimedia);
        mantDto.setListaPersonalizacion(listaPersonalizacion);
        mantDto.setListaVariante(listaVariante);
        mantDto.setListaGrupoCategoria(listaGrupoCategoria);
        mantDto.setListaImg(listaImgDisenio);
        mantDto.setListaImgPropio(listaImgDisenioPropio);
        mantDto.setListaCategorias(listaCategoria);
        return mantDto;
    }

    @Transactional
    @Override
    public ProductoMantDto guardar(ProductoMantDto mantenimientoDto) throws Exception {
        String usuario = sellerCuentasRepository.validaObtenerCorreo(mantenimientoDto.getIdUsuarioLogeado());
        Productos productoEntity = productoMapper.toProductos(mantenimientoDto, usuario);
        if (mantenimientoDto.getListaCategorias().isEmpty()) {
            throw new ExceptionUser("Seleccione al menos una categoria");
        }
        ProductoMantDto.ProductoCategoriaDto categoriaDto = mantenimientoDto.getListaCategorias().get(0);
        CategoriasGrupomenu categoriasGrupomenu = categoriasGrupomenuRepository.findById(categoriaDto.getIdCategoriaGrupomenu()).get();
        String lastSku = productosRepository.ObtenerUltimoSku(mantenimientoDto.getIdSeller());
        String sku = Util.getCorrelativoSku(mantenimientoDto.getIdSeller(), lastSku, mantenimientoDto.getCodigoMenu(), categoriasGrupomenu.getIdGrupomenu().getIdGrupo().getId().toString(), categoriasGrupomenu.getIdCategoria().getCodigo());
        productoEntity.setSku(sku);
        productosRepository.save(productoEntity);
        List<ProductoAssetsMultimedia> listaMultimedia = mantenimientoDto.getListaMultimedia().stream().map(mul -> productoMapper.toProductoAssetsMultimedia(mul, productoEntity.getId(), "usu")).collect(Collectors.toList());
        assetsMultimediaRepository.saveAll(listaMultimedia);
        List<ProductoVariantes> listaVariante = mantenimientoDto.getListaVariante().stream().map(pro -> productoMapper.toProductoVariantes(pro, productoEntity.getId(), productoEntity.getSku())).collect(Collectors.toList());
        productoVariantesRepository.saveAll(listaVariante);
        ProductoDimensiones listaDimension = productoMapper.toProductoDimensiones(mantenimientoDto.getDimension(), productoEntity.getId());
        dimensionesRepository.save(listaDimension);
        List<DigProductoTipopersonalizacion> listaPersonalizacion = mantenimientoDto.getListaPersonalizacion().stream().map(pro -> productoMapper.toDigProductoTipopersonalizacion(pro, productoEntity.getId())).collect(Collectors.toList());
        digProductoTipopersonalizacionRepository.saveAll(listaPersonalizacion);
        List<ProductoImagen> listaImgDisenio = mantenimientoDto.getListaImg().stream().map(pro -> productoMapper.toProductoImagen(pro, productoEntity.getId())).collect(Collectors.toList());
        imgdisenioRepository.saveAll(listaImgDisenio);
        List<ProductoImagenPropio> listaImgDisenioPropio = mantenimientoDto.getListaImgPropio().stream().map(pro -> productoMapper.toProductoImagenPropio(pro, productoEntity.getId())).collect(Collectors.toList());
        imagenPropioRepository.saveAll(listaImgDisenioPropio);
        List<ProductoCatgrupmenu> listaProductoCatgrupmenu = mantenimientoDto.getListaCategorias().stream().map(pro -> productoMapper.toProductoCatgrupmenu(pro, productoEntity.getId())).collect(Collectors.toList());
        productoCatgrupmenuRepository.saveAll(listaProductoCatgrupmenu);
        return mantenimientoDto;
    }

    @Override
    @Transactional
    public ProductoMantDto actualizar(ProductoMantDto mantenimientoDto) throws Exception {
        String usuario = sellerCuentasRepository.validaObtenerCorreo(mantenimientoDto.getIdUsuarioLogeado());
        Optional<Productos> optProducto = productosRepository.findById(mantenimientoDto.getId());
        if (!optProducto.isPresent()) {
            throw new ExceptionUser("El producto no esta registrado");
        }
        Productos productoBd = optProducto.get();
        Productos productoEntity = productoMapper.toProductos(mantenimientoDto, usuario);
        productoEntity.setFecRegistro(productoBd.getFecRegistro());
        productoEntity.setUsuRegistro(productoBd.getUsuRegistro());
        ProductoMantDto.ProductoCategoriaDto categoriaDto = mantenimientoDto.getListaCategorias().get(0);
        CategoriasGrupomenu categoriasGrupomenu = categoriasGrupomenuRepository.findById(categoriaDto.getIdCategoriaGrupomenu()).get();
        String lastSku = productosRepository.ObtenerUltimoSku(mantenimientoDto.getIdSeller());
        String sku = Util.getCorrelativoSku(mantenimientoDto.getIdSeller(), lastSku, mantenimientoDto.getCodigoMenu(), categoriasGrupomenu.getIdGrupomenu().getIdGrupo().getId().toString(), categoriasGrupomenu.getIdCategoria().getCodigo());
        productoEntity.setSku(sku);

        List<Integer> listaMultimediaBd = productoBd.getProductoAssetsMultimediaSet().stream().map(x -> x.getId()).collect(Collectors.toList());
        List<Integer> listaVarianteBd = productoBd.getProductoVariantesSet().stream().map(x -> x.getId()).collect(Collectors.toList());
        List<Integer> listaDimensionBd = productoBd.getProductoDimensionesSet().stream().map(x -> x.getId()).collect(Collectors.toList());
        List<Integer> listaPersonalizacionBd = productoBd.getDigProductoTipopersonalizacionSet().stream().map(x -> x.getId()).collect(Collectors.toList());
        List<Integer> listaImagenBd = productoBd.getProductoImagenSet().stream().map(x -> x.getId()).collect(Collectors.toList());
        List<Integer> listaImagenPropiaBd = productoBd.getProductoImagenPropioSet().stream().map(x -> x.getId()).collect(Collectors.toList());
        List<Integer> listaProductoCatgrupmenuBd = productoBd.getProductoCatgrupmenuSet().stream().map(x -> x.getId()).collect(Collectors.toList());

        List<ProductoAssetsMultimedia> listaMultimedia = mantenimientoDto.getListaMultimedia().stream().map(mul -> productoMapper.toProductoAssetsMultimedia(mul, productoEntity.getId(), "usu")).collect(Collectors.toList());
        List<ProductoVariantes> listaVariante = mantenimientoDto.getListaVariante().stream().map(pro -> productoMapper.toProductoVariantes(pro, productoBd.getId(), productoEntity.getSku())).collect(Collectors.toList());
        ProductoDimensiones listaDimension = productoMapper.toProductoDimensiones(mantenimientoDto.getDimension(), productoEntity.getId());
        List<DigProductoTipopersonalizacion> listaPersonalizacion = mantenimientoDto.getListaPersonalizacion().stream().map(pro -> productoMapper.toDigProductoTipopersonalizacion(pro, productoEntity.getId())).collect(Collectors.toList());
        List<ProductoImagen> listaImgDisenio = mantenimientoDto.getListaImg().stream().map(pro -> productoMapper.toProductoImagen(pro, productoEntity.getId())).collect(Collectors.toList());
        List<ProductoImagenPropio> listaImgDisenioPropio = mantenimientoDto.getListaImgPropio().stream().map(pro -> productoMapper.toProductoImagenPropio(pro, productoEntity.getId())).collect(Collectors.toList());
        List<ProductoCatgrupmenu> listaProductoCatgrupmenu = mantenimientoDto.getListaCategorias().stream().map(pro -> productoMapper.toProductoCatgrupmenu(pro, productoEntity.getId())).collect(Collectors.toList());

        productosRepository.save(productoEntity);
        assetsMultimediaRepository.delete(listaMultimediaBd);
        productoVariantesRepository.delete(listaVarianteBd);
        dimensionesRepository.delete(listaDimensionBd);
        digProductoTipopersonalizacionRepository.delete(listaPersonalizacionBd);
        imgdisenioRepository.delete(listaImagenBd);
        imagenPropioRepository.delete(listaImagenPropiaBd);
        productoCatgrupmenuRepository.delete(listaProductoCatgrupmenuBd);

        assetsMultimediaRepository.saveAll(listaMultimedia);
        productoVariantesRepository.saveAll(listaVariante);
        dimensionesRepository.save(listaDimension);
        digProductoTipopersonalizacionRepository.saveAll(listaPersonalizacion);
        imgdisenioRepository.saveAll(listaImgDisenio);
        imagenPropioRepository.saveAll(listaImgDisenioPropio);
        productoCatgrupmenuRepository.saveAll(listaProductoCatgrupmenu);

        return mantenimientoDto;
    }

    @Transactional
    @Override
    public void eliminar(int ipPersonaLogeada, List<Integer> listaId) throws Exception {
        String usuario = sellerCuentasRepository.validaObtenerCorreo(ipPersonaLogeada);
        List<Productos> listaProductoBd = Lists.newArrayList(productosRepository.findAllById(listaId));
        if (listaProductoBd.isEmpty()) {
            throw new ExceptionUser("El producto no esta registrado");
        }
        for (Productos productoBd : listaProductoBd) {
            List<ProductoAssetsMultimedia> listaMultimediaBd = Lists.newArrayList(productoBd.getProductoAssetsMultimediaSet());
            assetsMultimediaRepository.deleteAll(listaMultimediaBd);
            List<ProductoVariantes> listaVarianteBd = Lists.newArrayList(productoBd.getProductoVariantesSet());
            productoVariantesRepository.deleteAll(listaVarianteBd);
            List<ProductoDimensiones> listaDimensionBd = Lists.newArrayList(productoBd.getProductoDimensionesSet());
            dimensionesRepository.deleteAll(listaDimensionBd);
            List<DigProductoTipopersonalizacion> listaPersonalizacionBd = Lists.newArrayList(productoBd.getDigProductoTipopersonalizacionSet());
            digProductoTipopersonalizacionRepository.deleteAll(listaPersonalizacionBd);
            List<ProductoImagen> listaImagenBd = Lists.newArrayList(productoBd.getProductoImagenSet());
            imgdisenioRepository.deleteAll(listaImagenBd);
            List<ProductoImagenPropio> listaImagenPropiaBd = Lists.newArrayList(productoBd.getProductoImagenPropioSet());
            imagenPropioRepository.deleteAll(listaImagenPropiaBd);
            List<ProductoCatgrupmenu> listaProductoCatgrupmenuBd = Lists.newArrayList(productoBd.getProductoCatgrupmenuSet());
            productoCatgrupmenuRepository.deleteAll(listaProductoCatgrupmenuBd);
            productosRepository.delete(productoBd);
        }
    }

    private List<MenuGrupoCategoriaDto> listarCategoriaDisenio(int idProducto) throws Exception {
        List<GrupoCategoriaDisenioEntity> categoriasGrupomenuEntitys = digImgdisenioSubcategoriasRepository.listarGrupoCategoriaDisenioProducto(idProducto);
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
    public void ver(int idPersonaLogeada, int idProducto) throws Exception {
        String usuario = sellerCuentasRepository.validaObtenerCorreo(idPersonaLogeada);
        Optional<Productos> optProducto = productosRepository.findById(idProducto);
        if (!optProducto.isPresent()) {
            throw new ExceptionUser("El producto no esta registrado");
        }
        Productos productoBd = optProducto.get();
        productoBd.setOnlinestatus(productoBd.getOnlinestatus() == 0 ? 1 : 0);
        productoBd.setUsuActualizacion(usuario);
        productoBd.setFecActualizacion(new Date());
        productosRepository.save(productoBd);
    }

    @Override
    public void destacar(int idPersonaLogeada, int idProducto) throws Exception {
        String usuario = sellerCuentasRepository.validaObtenerCorreo(idPersonaLogeada);
        Optional<Productos> optProducto = productosRepository.findById(idProducto);
        if (!optProducto.isPresent()) {
            throw new ExceptionUser("El producto no esta registrado");
        }
        Productos productoBd = optProducto.get();
        productoBd.setDestacado(productoBd.getDestacado() == null || productoBd.getDestacado() == 0 ? 1 : 0);
        productoBd.setUsuActualizacion(usuario);
        productoBd.setFecActualizacion(new Date());
        productosRepository.save(productoBd);
    }
}
