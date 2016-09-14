<%-- 
    Document   : login
    Created on : 14-sep-2016, 9:35:38
    Author     : Matias
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="cabeceras.jsp" />
        <title>Login</title>
        <script type="text/javascript">
            $().ready(function () {
                $("#formulario_login").validate({
                    rules: {
                        usuario: {
                            required: true,
                            minlength: 3,
                            maxlength: 15
                        },
                        contrasenia: {
                            required: true,
                            minlength: 3,
                            maxlength: 15
                        }
                    },
                    messages: {
                        usuario: {
                            required: "Campo obligatorio",
                            minlength: "Longitud minima 3 carácteres",
                            maxlength: "Longitud maxima 20 carácteres"
                        },
                        contrasenia: {
                            required: "Campo obligatorio",
                            minlength: "Longitud minima 3 carácteres",
                            maxlength: "Longitud maxima 20 carácteres"
                        }
                    }
                });
            });
        </script>
    </head>
    <body>
       <form class="form-horizontal" method="POST" action="sesion" id="formulario_login">
            <div class="container">
                    <p></p>
                    <div class="col-md-10">
                        <div class="panel-body">Inico de Sesion</div>
                            <div class="panel-footer">
                                <ul class="list-group">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label" for="usuario">Usuario </label>
                                        <div class="col-sm-10"> <input name="usuario" type="text" > </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label" for="contrasenia">Contraseña </label>
                                        <div class="col-sm-10"> <input  name="contrasenia" type="password"> </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-10">
                                            <button class="btn btn-info" type="submit">Ingresar</button>
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
        </form>
    </body>
</html>