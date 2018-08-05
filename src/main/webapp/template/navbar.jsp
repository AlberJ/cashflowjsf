<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container">
		<div class="nav">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/controller.do?op=conmov"><span
				class="glyphicon glyphicon-home"></span> <img
				src="${pageContext.request.contextPath}/template/imagens/open-iconic-master/png/home-3x.png">
				Home</a>
			<!-- COMPACTA AS OPÇÕES PARA TELAS PEQUENAS -->
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active"><a class="nav-link"
						href="${pageContext.request.contextPath}/controller.do?op=conmov"> <span
							class="glyphicon glyphicon-th-list"> <img
								src="${pageContext.request.contextPath}/template/imagens/open-iconic-master/png/list-2x.png">
								Movimentações
						</span>
					</a></li>

					<li class="nav-item active"><a class="nav-link"
						href="${pageContext.request.contextPath}/movimentacao/cadastro.jsp">
							<img
							src="${pageContext.request.contextPath}/template/imagens/open-iconic-master/png/plus-2x.png">
							Adicionar Movimentação
					</a></li>
				</ul>
			</div>
		</div>

		<div class="nav justify-content-end">
			<div class="navbar-brand" style="margin: 0 30px">
				<span class="glyphicon glyphicon-th-list"> <img
					src="${pageContext.request.contextPath}/template/imagens/money.png"
					style="width: 26px; height: 26px;"> <span
					class="text-secondary"> R$: </span> ${saldo }
				</span>
			</div>

		 	<ul class="navbar-nav mr-auto" style="margin: 0 16px"> 
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"><img
						src="${pageContext.request.contextPath}/template/imagens/open-iconic-master/png/person-2x.png">
						<span class="text-dark"> ${usuario.login } </span></a>

					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="#"> <img
							src="${pageContext.request.contextPath}/template/imagens/open-iconic-master/png/cog-2x.png">
							Editar dados
						</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="${pageContext.request.contextPath}/controller.do?op=logout"> <img
							src="${pageContext.request.contextPath}/template/imagens/open-iconic-master/png/account-logout-2x.png">
							Sair
						</a>
					</div></li>
		 	</ul> 
			
			
		</div>
	</div>
</nav>


