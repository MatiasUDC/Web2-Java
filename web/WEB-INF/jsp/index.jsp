<%-- 
    Document   : indexjsp
    Created on : 06-sep-2016, 1:05:41
    Author     : Matias
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src=" <c:url value="/bower_components/jquery/dist/jquery.min.js"></c:url>"></script>
        <script type="text/javascript" src=" <c:url value="/bower_components/bootstrap/dist/js/bootstrap.js"></c:url>"></script>
        <link rel="stylesheet" type="text/css" href=" <c:url value="/bower_components/bootstrap/dist/css/bootstrap.css"></c:url>">
        <script type="text/javascript" src=" <c:url value="/bower_components/jquery-ui/jquery-ui.js"></c:url>"></script>
        <script type="text/javascript" src=" <c:url value="/bower_components/jquery-validation/dist/jquery.validate.js"></c:url>"></script>
        <link rel="stylesheet" type="text/css" href=" <c:url value="/bower_components/jquery-ui/themes/base/jquery-ui.min.css"></c:url>">


            <title>Clientes</title>
        </head>
        <body>
            <div class="container">
                <div class="row">
                    <nav class="navbar navbar-default">
                        <div class="container-fluid">
                            <!-- Collect the nav links, forms, and other content for toggling -->
                            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                                <ul class="nav navbar-nav navbar-right">
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                            <span class="glyphicon glyphicon-user"></span>
                                            <span class="glyphicon glyphicon-triangle-bottom"></span>
                                        </a>
                                        <ul class="dropdown-menu">
                                            <li><a href="#">Perfil</a></li>
                                            <li role="separator" class="divider"></li>
                                            <li><a href="">Cerrar Session</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div><!-- /.navbar-collapse -->
                        </div><!-- /.container-fluid -->
                    </nav>
                    <div class="col-md-8">
                        <h3>Clientes <a href="alta"><small>Nuevo</small></a></h3>
                        <table class="table table-condensed">
                            <tr>
                                <td><strong>Nombre</strong></td>
                                <td><strong>Edad</strong></td>
                                <td><strong>Nacionalidad</strong></td>
                                <td><strong>Activo</strong></td>
                                <td></td>
                                <td></td>
                            </tr>
                        <c:forEach var="fila" items="${clientes}" >
                           
                                <tr>
                                    <td><c:out value="${fila['nombre']}"/></td>
                                    <td><c:out value="${fila['edad']}"/></td>
                                    <td><c:out value="${fila['nacionalidad']}"/></td>
                                    <c:choose>
                                        <c:when test="${fila.activo eq 1}">
                                            <td><span class="glyphicon glyphicon-ok"></span></td>   
                                            </c:when>    
                                            <c:otherwise>
                                            <td><span class="glyphicon glyphicon-remove"></span></td>
                                            </c:otherwise>
                                        </c:choose>
                                    <td>
                                        <a href="/FormularioModificacion?id=<c:out value="${fila['id']}"/>" class="btn btn-default" >
                                            <span class="glyphicon glyphicon-pencil"></span> Modificar
                                        </a>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModal">
                                            <span class="glyphicon glyphicon-trash"></span> Eliminar
                                        </button>
                                    </td>

                                    <!-- Modal -->
                                    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                    <h4 class="modal-title" id="myModalLabel">Eliminar Registro</h4>
                                                </div>
                                                <div class="modal-body">
                                                    ¿Esta seguro de eliminar el registro?
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                                    <a class="btn btn-danger" href="/ProcesarBaja?id=<c:out value="${fila['id']}"/>">Eliminar</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </tr>
                            </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
