import { IMenu } from '@core/models/menu.model';
export class PathApp {
  public static readonly routeLogin = 'login';
  public static readonly routeIndex = 'index';
  public static readonly routeDescarga = 'descargas';
  public static readonly routeDashboard = 'dashboard';
  public static readonly routeSeller = 'empresa/seller';
  public static readonly routeSellerRegistro = 'empresa/seller/registro';
  public static readonly routeSellerRegistroNuevo =
    'empresa/seller/registro/-1';
  public static readonly routeNegocio = 'empresa/negocio';
  public static readonly routeColaborador = 'empresa/colaborador';
  public static readonly routePerfil = 'empresa/perfil';
  public static readonly routeColSeller = 'empresa/colaborador/seller';
  public static readonly routeColSellerReg =
    'empresa/colaborador/seller/registro';
  public static readonly routeColSellerRegNuevo =
    'empresa/colaborador/seller/registro/-1';
  public static readonly routeColBmoi = 'empresa/colaborador/bmoi';
  public static readonly routeColBmoiReg = 'empresa/colaborador/bmoi/registro';
  public static readonly routeColBmoiRegNuevo =
    'empresa/colaborador/bmoi/registro/-1';
  public static readonly routeSellerReg = 'empresa/cliente/registro';
  public static readonly routeSellerRegNuevo = 'empresa/cliente/registro/-1';
  public static readonly routeNegocioRegistro = 'empresa/negocio/registro';
  public static readonly routeProducto = 'producto';
  public static readonly routeProductoRegistro = 'producto/registro';
  public static readonly routeProductoRegistroNuevo = 'producto/registro/-1';
  public static readonly routeDispositivo = 'producto/dispositivo';
  public static readonly routeDispositivoRegistro =
    'producto/dispositivo/registro';
  public static readonly routeUnidadMedida = 'producto/unidad-medida';
  public static readonly routeSensorMedida = 'producto/sensor-medida';
  public static readonly routeSensorMedidaModelo = 'producto/modelo-sensor';
  public static readonly routeVentaSolicitud = 'venta/solicitud';
  public static readonly routeVentaSolicitudRegistro =
    'venta/solicitud/registro';
  public static readonly routeVentaNegociacion = 'venta/negociacion';
  public static readonly routeVentaOrdenCompra = 'venta/orden-compra';
  public static readonly routeVentaOrdenCompraRegistro =
    'venta/orden-compra/registro';
  public static readonly routeVentaOrdenCompraDetalle =
    'venta/orden-compra/detalle';
  public static readonly routeVentaPlanConectividad = 'venta/plan-conectividad';
  public static readonly routeVentaServicio = 'venta/servicio';
  public static readonly routeTipoCambio = 'venta/tipo-cambio';
  public static readonly routeProveedor = 'proveedor';
  public static readonly routeProveedorRegistro = 'proveedor/registro';
  public static readonly routeCuentaSeller = 'cuenta/seller';
  public static readonly routeCuentaSellerRegistro = 'cuenta/seller/registro';
  public static readonly routeCuentaSellerRegistroNuevo =
    'cuenta/seller/registro/-1';
  public static readonly routeCuentaBmoi = 'cuenta/bmoi';
  public static readonly routeCuentaBmoiRegistro = 'cuenta/bmoi/registro';
  public static readonly routeCuentaBmoiRegistroNuevo =
    'cuenta/bmoi/registro/-1';
  public static readonly routeCuentaUsuarioNegocio = 'cuenta/colaborador';
  public static readonly routeCuentaRol = 'cuenta/colaborador';
  public static readonly routeReporteDispositivo = 'reporte/dispositivo';
  public static readonly routeReporteDispositivoSemanal =
    'reporte/dispositivo/resumen';
  public static readonly routeReporteConsolidado = 'reporte/consolidado';
  public static appSeller: IMenu[] = [
    {
      route: 'producto',
      title: 'Productos',
      icon: 'chevron_left',
      children: [
        {
          route: PathApp.routeProducto,
          title: 'Listar',
        },
        {
          route: PathApp.routeProductoRegistroNuevo,
          title: 'Agregar',
        },
      ],
    },
    {
      route: 'empresa',
      title: 'Colaboradores',
      icon: 'chevron_left',
      children: [
        {
          route: PathApp.routeColSeller,
          title: 'Listar',
        },
        {
          route: PathApp.routeColSellerRegNuevo,
          title: 'Agregar',
        },
      ],
    },
    {
      route: 'cuenta',
      title: 'Cuentas',
      icon: 'chevron_left',
      children: [
        {
          route: PathApp.routeCuentaSeller,
          title: 'Listar',
        },
        {
          route: PathApp.routeCuentaSellerRegistroNuevo,
          title: 'Agregar',
        },
      ],
    },
  ];
  public static appBmoi: IMenu[] = [
    {
      route: 'producto',
      title: 'Productos',
      icon: 'chevron_left',
      children: [
        {
          route: PathApp.routeProducto,
          title: 'Listar',
        },
      ],
    },
    {
      title: 'Sellers',
      route: 'empresa',
      icon: 'chevron_left',
      children: [
        {
          route: PathApp.routeSeller,
          title: 'Listar',
        },
        {
          route: PathApp.routeSellerRegistroNuevo,
          title: 'Agregar',
        },
      ],
    },
    {
      route: 'empresa',
      title: 'Colaboradores',
      icon: 'chevron_left',
      children: [
        {
          route: PathApp.routeColBmoi,
          title: 'Listar',
        },
        {
          route: PathApp.routeColBmoiRegNuevo,
          title: 'Agregar',
        },
      ],
    },
    {
      route: 'cuenta',
      title: 'Cuentas',
      icon: 'chevron_left',
      children: [
        {
          route: PathApp.routeCuentaBmoi,
          title: 'Listar',
        },
        {
          route: PathApp.routeCuentaBmoiRegistroNuevo,
          title: 'Agregar',
        },
      ],
    },
  ];
}
