function isElementTotallyVisible(elto) {
  var anchoViewport = window.innerWidth || document.documentElement.clientWidth;
  var alturaViewport =
    window.innerHeight || document.documentElement.clientHeight;
  //Posición de la caja del elemento
  var caja = elto.getBoundingClientRect();
  return (
    caja.top >= 0 &&
    caja.bottom <= alturaViewport &&
    caja.left >= 0 &&
    caja.right <= anchoViewport
  );
}

function isElementPartiallyVisible(elto) {
  var anchoViewport = window.innerWidth || document.documentElement.clientWidth;
  var alturaViewport =
    window.innerHeight || document.documentElement.clientHeight;
  //Posición de la caja del elemento
  var caja = elto.getBoundingClientRect();
  var cajaDentroH =
    (caja.left >= 0 && caja.left <= anchoViewport) ||
    (caja.right >= 0 && caja.right <= anchoViewport);
  var cajaDentroV =
    (caja.top >= 0 && caja.top <= alturaViewport) ||
    (caja.bottom >= 0 && caja.bottom <= alturaViewport);
  return cajaDentroH && cajaDentroV;
}

function inViewportPartially(elto, handler) {
  var anteriorVisibilidad = isElementPartiallyVisible(elto); //crea una clausura para el manejador de este evento concreto
  //Defino un manejador para determinar posibles cambios
  function detectarPosibleCambio() {
    var esVisible = isElementPartiallyVisible(elto);
    if (esVisible != anteriorVisibilidad) {
      //ha cambiado el estado de visibilidad del elemento
      anteriorVisibilidad = esVisible;
      if (typeof handler == "function") handler(esVisible, elto);
    }
  }
  window.addEventListener("load", detectarPosibleCambio);
  window.addEventListener("resize", detectarPosibleCambio);
  window.addEventListener("scroll", detectarPosibleCambio);
}

function inViewportTotally(elto, handler) {
  var anteriorVisibilidad = isElementTotallyVisible(elto); //crea una clausura para el manejador de este evento concreto
  //Defino un manejador para determinar posibles cambios
  function detectarPosibleCambio() {
    var esVisible = isElementTotallyVisible(elto);
    if (esVisible != anteriorVisibilidad) {
      //ha cambiado el estado de visibilidad del elemento
      anteriorVisibilidad = esVisible;
      if (typeof handler == "function") handler(esVisible, elto);
    }
  }
  //Asocio esta función interna a los diversos eventos que podrían producir un cambio en la visibilidad
  window.addEventListener("load", detectarPosibleCambio);
  window.addEventListener("resize", detectarPosibleCambio);
  window.addEventListener("scroll", detectarPosibleCambio);
}
