<%-- 
    Document   : alta_fomulario
    Created on : 07-sep-2016, 3:08:38
    Author     : Matias
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="cabeceras.jsp"/>
<title>Formulario Modificacion</title>
            <script type="text/javascript" src=" <c:url value="/bower_components/jquery-validation/jquery.ui.datepicker.validation.min.js"></c:url>"></script>
            <script type="text/javascript">
                $().ready(function () {
                    jQuery.validator.addMethod('selectcheck', function (value) {
                        return (value != '0');
                    }, "Campo obligatorio");
                    $("#formulario_modificacion").validate({
                        rules: {
                            nombre: {
                                required: true,
                                minlength: 3,
                                maxlength: 20
                            },
                            apellido: {
                                required: true,
                                minlength: 3,
                                maxlength: 20
                            },
                            fecha_nacimiento: {
                                required: true,
                                dpDate: true
                            },
                            nacionalidad: {
                                selectcheck: true
                            }
                        },
                        messages: {
                            nombre: {
                                required: "Campo obligatorio",
                                minlength: "Longitud minima 3 carácteres",
                                maxlength: "Longitud maxima 20 carácteres"
                            },
                            apellido: {
                                required: "Campo obligatorio",
                                minlength: "Longitud minima 3 carácteres",
                                maxlength: "Longitud maxima 20 carácteres"
                            },
                            fecha_nacimiento: {
                                required: "Campo obligatorio"
                            }
                        }
                    });
                });
                $(function () {
                    $("#datepicker").datepicker({minDate: new Date('1920-01-01'), maxDate: new Date(), changeYear: true, changeMonth: true});
                });
            </script>
        </head>
        <body>
            <script>
                $(function () {
                    $("#datepicker").datepicker({minDate: new Date('1920-01-01'), maxDate: new Date(), changeYear: true, changeMonth: true});
                });
            </script>
            <form class="form-horizontal" method="POST" action="Modificacion" id="formulario_modificacion">
                <div class="container">
                    <div class="row-flow">
                        <p></p>
                        <div class="col-md-10">
                            <div class="panel panel-success">

                                <div class="panel-body">Formulario de Modificacion</div>

                                <div class="panel-footer">
                                    <ul class="list-group">

                                        <div class="form-group">
                                            <label class="col-sm-2 control-label" for="nombre">Nombre : </label>
                                            <div class="col-sm-10"> <input name="nombre" type="text" value="<c:out value="${cliente['nombre']}"/>" > </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label" for="apellido">Apellido : </label>
                                        <div class="col-sm-10"> <input name="apellido" type="text" value="<c:out value="${cliente['apellido']}"/>"> </div>
                                    </div>                            

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label" for="fecha_nacimiento">Fecha de Nacimiento : </label>
                                        <div class="col-sm-10"><input name="fecha_nacimiento" type="text" id="datepicker" value="<c:out value="${cliente['fecha_nacimiento']}"/>"></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label" for="nacionalidad">Nacionalidad : </label>
                                        <div class="col-sm-10">
                                            <select id="localidad" name="nacionalidad">
                                                <optgroup>
                                                    <option value="<c:out value="${cliente['id_nacionalidad']}"/>" selected><c:out value="${cliente['nacionalidad']}"/></option>
                                                </optgroup>
                                                <!--<option selected></option>-->
                                                <optgroup label="-------------------------------------">
                                                    <c:forEach var="fila" items="${nacionalidades}" >
                                                        <option value="<c:out value="${fila['id']}"/>"><c:out value="${fila['descripcion']}"/></option>
                                                    </c:forEach>
                                                </optgroup>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label" for="activo">Activo : </label>
                                        <select id="activo" name="activo">
                                            <optgroup>
                                                <option value="<c:out value="${cliente['activo']}"/>" selected><c:out value="${cliente['activo']}"/></option>
                                            </optgroup>
                                            <optgroup label="-------------------------------------">
                                                 <option value="1">1</option>
                                                  <option value="0">0</option>
                                            </optgroup>
                                        </select>
                                    </div>            
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-10">
                                            <button class="btn btn-default" type="submit">Guardar</button>

                                            <button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal">Cancelar</button>

                                            <!-- Modal -->
                                            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                            <h4 class="modal-title" id="myModalLabel">Cancelar creacion de cliente</h4>
                                                        </div>
                                                        <div class="modal-body">
                                                            Se perderan todos los datos completados<br/>¿Esta seguro de continuar?
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                                            <a href="index" class="btn btn-danger">Continuar</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <c:if test="${not empty errores}"><!-- muestro errores-->
                                            <p>Error al prosesar el formulario</p>
                                            <ul>
                                                <c:forEach var="fila" items="${errores}" >
                                                    <li style="color: red;"><c:out value="${fila.value}"/></li>
                                                    </c:forEach>
                                            </ul>
                                        </c:if>
                                    </div>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>