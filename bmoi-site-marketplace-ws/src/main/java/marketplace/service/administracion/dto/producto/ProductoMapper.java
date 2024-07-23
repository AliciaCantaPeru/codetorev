/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.producto;

import java.util.Date;
import java.util.List;
import marketplace.repository.entity.DigProductoTipopersonalizacion;
import marketplace.repository.entity.ProductoAssetsMultimedia;
import marketplace.repository.entity.ProductoCatgrupmenu;
import marketplace.repository.entity.ProductoDimensiones;
import marketplace.repository.entity.ProductoImagen;
import marketplace.repository.entity.ProductoImagenPropio;
import marketplace.repository.entity.ProductoVariantes;
import marketplace.repository.entity.Productos;
import marketplace.util.Util;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
@Mapper(componentModel = "spring")
public interface ProductoMapper {

    List<ProductoOutDto> toProductoMantDtos(List<Productos> listaProductos);

    ProductoOutDto toProductoOutDto(Productos conectividad);

    // toDto
    @BeforeMapping
    default void beforeMapping(@MappingTarget ProductoMantDto target, Productos source) {
        if (source.getProductonuevo() != null) {
            String[] splitProNuevo = source.getProductonuevo().split(Util.SEPARADOR);
            target.setDescuentoProdNuevo(Integer.parseInt(splitProNuevo[0]) == 1);
            target.setPorcentajeProdNuevo(Integer.parseInt(splitProNuevo[1]));
            target.setFechaInicioProdNuevo(Util.getFecha(splitProNuevo[2]));
            target.setFechaFinProdNuevo(Util.getFecha(splitProNuevo[3]));
        }
        target.setPersonalizable(source.getPersonalizable() == 1);
        target.setOnlinestatus(source.getOnlinestatus() == 1);
        target.setEnviogratis(source.getEnviogratis() == 1);

    }

    @Mappings({
        @Mapping(target = "idSeller", source = "idSeller.id"),
        @Mapping(target = "idBrand", source = "idBrand.id"),
        @Mapping(target = "codigoMenu", source = "codigoMenu.codigo"),
        @Mapping(target = "idPrdpadre", source = "idPrdpadre.id"),
        @Mapping(target = "onlinestatus", ignore = true),
        @Mapping(target = "personalizable", ignore = true),
        @Mapping(target = "enviogratis", ignore = true)
    })
    ProductoMantDto toProductoMantDto(Productos productos);

    ProductoMantDto.ProductoAssetsMultimediaDto toMultimediaAssetsDto(ProductoAssetsMultimedia assetsMultimedia);

    @Mappings({
        @Mapping(target = "idColor", source = "idColor.id"),
        @Mapping(target = "codColor", source = "idColor.codigo"),
        @Mapping(target = "idTalla", source = "idTalla.id"),
        @Mapping(target = "codTalla", source = "idTalla.codigo")
    })
    ProductoMantDto.ProductoVariantesDto toProductoVarianteDto(ProductoVariantes productoVariantes);

    ProductoMantDto.ProductoDimensionesDto toProductoDimensionDto(ProductoDimensiones productoVariantes);

    @Mappings({
        @Mapping(target = "idDigtipopersonalizacion", source = "idDigtipopersonalizacion.id")
    })
    ProductoMantDto.DigProductoTipopersonalizacionDto toDigProductoTipopersonalizacionDto(DigProductoTipopersonalizacion digProductoTipopersonalizacion);

    @Mappings({
        @Mapping(target = "idDigImgSubImg", source = "idDigImgSubImg.id"),})
    ProductoMantDto.ProductoImagenDto toProductoImagenDto(ProductoImagen productoImagen);

    @Mappings({
        @Mapping(target = "idDigImgSubImg", source = "idDigImgSubImg.id"),
        @Mapping(target = "idGrupo", source = "idDigImgSubImg.idCategoria.idMenu.id"),
        @Mapping(target = "idCategoria", source = "idDigImgSubImg.idCategoria.id"),})
    ProductoMantDto.ProductoImagenPropioDto toProductoImagenPropioDto(ProductoImagenPropio productoImagenPropio);

    @Mappings({
        @Mapping(target = "idCategoriaGrupomenu", source = "idCategoriaGrupomenu.id")
    })
    ProductoMantDto.ProductoCategoriaDto toProductoCategoriaDto(ProductoCatgrupmenu catgrupmenu);

    // entity
    @BeforeMapping
    default void beforeMapping(@MappingTarget Productos target, ProductoMantDto source, String usuario) {
        if (source.getId() == null) {
            target.setFecRegistro(new Date());
            target.setUsuRegistro(usuario);
        } else {
            target.setFecActualizacion(new Date());
            target.setUsuActualizacion(usuario);
        }
        if (source.getIdPrdpadre() != null) {
            target.setIdPrdpadre(new Productos(source.getIdPrdpadre()));
        }
        String descuento = String.valueOf(source.isDescuentoProdNuevo() ? 1 : 0);
        String porcentaje = String.valueOf(source.getPorcentajeProdNuevo());
        String fechaInicio = Util.getFechaString(source.getFechaInicioProdNuevo());
        String fechaFin = Util.getFechaString(source.getFechaFinProdNuevo());
        target.setProductonuevo(descuento + Util.SEPARADOR + porcentaje + Util.SEPARADOR + fechaInicio + Util.SEPARADOR + fechaFin);
        target.setPersonalizable(source.isPersonalizable() ? 1 : 0);
        target.setOnlinestatus(source.isOnlinestatus() ? 1 : 0);
        target.setEnviogratis(source.isEnviogratis() ? 1 : 0);
    }

    @Mappings({
        @Mapping(target = "idSeller.id", source = "dto.idSeller"),
        @Mapping(target = "idBrand.id", source = "dto.idBrand"),
        @Mapping(target = "codigoMenu.codigo", source = "dto.codigoMenu"),
        @Mapping(target = "idPrdpadre", ignore = true),
        @Mapping(target = "onlinestatus", ignore = true),
        @Mapping(target = "personalizable", ignore = true),
        @Mapping(target = "enviogratis", ignore = true),
        @Mapping(target = "productonuevo", ignore = true),
        @Mapping(target = "usuActualizacion", ignore = true),
        @Mapping(target = "fecActualizacion", ignore = true),
        @Mapping(target = "sku", ignore = true)

    })
    Productos toProductos(ProductoMantDto dto, String usuario);

    @BeforeMapping
    default void beforeMapping(@MappingTarget ProductoAssetsMultimedia target, ProductoMantDto.ProductoAssetsMultimediaDto source, Integer idProducto, String usuario) {
        target.setFecRegistro(new Date());
        target.setUsuRegistro(usuario);
    }

    @Mappings({
        @Mapping(target = "idProducto.id", source = "idProducto")
    })
    ProductoAssetsMultimedia toProductoAssetsMultimedia(ProductoMantDto.ProductoAssetsMultimediaDto dto, Integer idProducto, String usuario);

    @BeforeMapping
    default void beforeMapping(@MappingTarget ProductoVariantes target, ProductoMantDto.ProductoVariantesDto source, Integer idProducto, String skuProducto) {
        String skuVariante = skuProducto + Util.SEPARADOR_GUION + source.getCodColor() + Util.SEPARADOR_GUION + source.getCodTalla();
        target.setSkuvariante(skuVariante);
    }

    @Mappings({
        @Mapping(target = "idProducto.id", source = "idProducto"),
        @Mapping(target = "idColor.id", source = "dto.idColor"),
        @Mapping(target = "idTalla.id", source = "dto.idTalla")
    })
    ProductoVariantes toProductoVariantes(ProductoMantDto.ProductoVariantesDto dto, Integer idProducto, String skuProducto);

    @Mappings({
        @Mapping(target = "idProducto.id", source = "idProducto")
    })
    ProductoDimensiones toProductoDimensiones(ProductoMantDto.ProductoDimensionesDto dto, Integer idProducto);

    @Mappings({
        @Mapping(target = "idProducto.id", source = "idProducto"),
        @Mapping(target = "idDigtipopersonalizacion.id", source = "dto.idDigtipopersonalizacion")
    })
    DigProductoTipopersonalizacion toDigProductoTipopersonalizacion(ProductoMantDto.DigProductoTipopersonalizacionDto dto, Integer idProducto);

    @Mappings({
        @Mapping(target = "idProducto.id", source = "idProducto"),
        @Mapping(target = "idDigImgSubImg.id", source = "productoImagen.idDigImgSubImg")
    })
    ProductoImagen toProductoImagen(ProductoMantDto.ProductoImagenDto productoImagen, Integer idProducto);

    @Mappings({
        @Mapping(target = "idProducto.id", source = "idProducto"),
        @Mapping(target = "idDigImgSubImg.id", source = "productoImagenPropio.idDigImgSubImg")
    })
    ProductoImagenPropio toProductoImagenPropio(ProductoMantDto.ProductoImagenPropioDto productoImagenPropio, Integer idProducto);

    @Mappings({
        @Mapping(target = "idProducto.id", source = "idProducto"),
        @Mapping(target = "idCategoriaGrupomenu.id", source = "categoriaDto.idCategoriaGrupomenu")
    })
    ProductoCatgrupmenu toProductoCatgrupmenu(ProductoMantDto.ProductoCategoriaDto categoriaDto, Integer idProducto);

}
