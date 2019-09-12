create database projeto_livraria;
use projeto_livraria;
create table usuario(
cd_usuario int(5) auto_increment not null primary key,
    nm_usuario varchar (50) not null,
    cpf_usuario int(11) not null,
    sexo char(1) not null,
    data_nascimento date not null,
    email varchar(30) not null,
    celular int(12) not null,
    perfil varchar(20) not null,
    login varchar(10) not null,
    senha varchar(16) not null
);

create table fornecedor(
	cd_fornecedor int(3) auto_increment not null primary key,
    nm_fornecedor varchar(50) not null,
    nm_fantasia varchar(50) not null,
    rz_social varchar(50) not null,
    cnpj int(11) not null,
    email varchar(30) not null, 
    telefone int(11),
    celular int(12) not null    
);

create table endereco(
	cd_endereco int(3) auto_increment not null primary key, 
    rua varchar(50) not null,
    bairro varchar(50) not null, 
    cidade varchar(30) not null, 
    estado char(2) not null,
    pais char(2) not null,
    cep int(8) not null,
    numero int(5) not null,
    cd_usuario int(5),
    cd_fornecedor int(3),
    foreign key (cd_usuario) references usuario(cd_usuario),
    foreign key (cd_fornecedor) references fornecedor(cd_fornecedor)
);


create table livro(
	cd_livro int(5) not null auto_increment primary key,
	nm_livro varchar(50) not null,
    autor varchar(50) not null,
    editora varchar(50) not null,
    genero varchar(50) not null,
    ano_livro int (4) not null,
    edicao int,
    preco_venda float(12,2) not null,
    qnt_livro int not null,
    cd_fornecedor int(3),
    foreign key (cd_fornecedor) references fornecedor(cd_fornecedor),
	link_img varchar(200) not null
);

create table compra(
	cd_compra int(5) auto_increment not null primary key,
    dt_compra date not null,
    dt_entrega date not null,
    cd_fornecedor int(3) not null,
    foreign key(cd_fornecedor) references fornecedor(cd_fornecedor)
);

create table cupom(
	cd_cupom int(5) not null primary key auto_increment,
    vl_desconto float (12,2) not null,
    dt_aquisicao date not null,
    dt_vencimento date not null,
    cd_usuario int(5) not null,
    foreign key (cd_usuario) references usuario (cd_usuario)
);

create table venda(
	cd_venda int(5) auto_increment not null primary key,
    cd_usuario int(5) not null,
    foreign key (cd_usuario) references usuario(cd_usuario),
    qnt_livro int not null,
    preco_unitario float(12,2) not null,
    data_venda date not null,
    cd_cupom int(5),
    foreign key (cd_cupom) references cupom(cd_cupom),
    cd_endereco int (5) not null,
    foreign key (cd_endereco) references endereco(cd_endereco)
);

create table lista_desejo(
	cd_lista int(5) auto_increment not null primary key,
    cd_usuario int(5) not null
    
);

create table item_desejado(
	cd_itemDesejado int(5) auto_increment not null primary key,
    cd_listaDesejo int(5) not null,
    cd_usuario int(5) not null,
    cd_livro int(5) not null,
    foreign key (cd_livro) references livro(cd_livro),
    foreign key (cd_listaDesejo) references lista_desejo(cd_lista),
    foreign key (cd_usuario) references usuario(cd_usuario)
);

create table item_compra(
	cd_itemCompra int(5) not null auto_increment primary key,
    cd_livro int(5) not null,
    cd_compra int(5) not null,
    foreign key (cd_livro) references livro(cd_livro),
    foreign key (cd_compra) references compra(cd_compra),
	quantidade int(11) not null,
    preco_unitario float(12,2)    
);

create table carrinho_compra(
	cd_carrinhoCompra int(5) auto_increment not null primary key,
    cd_usuario int(5) not null,
    foreign key (cd_usuario) references usuario(cd_usuario)
);

create table item_venda(
	cd_itemVenda int(5) not null auto_increment primary key,
    cd_livro int(5) not null,
    cd_venda int(5) not null,
    cd_carrinhoCompra int(5) not null,
    foreign key (cd_livro) references livro(cd_livro),
    foreign key (cd_venda) references venda(cd_venda),
	foreign key (cd_carrinhoCompra) references carrinho_compra(cd_carrinhoCompra),
    quantidade int(11) not null,
    preco_unitario float(12,2)    
);

#drop database projeto_livraria;
insert into fornecedor values(003,'Gustavo da Moita', 'Gudamota', 'MCDonalts', 514524549,'gustavoMotoqueiro@gmail.com', 66666669, 966665698);

insert into livro values(00004,'Harry Potter e a Pedra Filosofal', 'J.K Rolling', 'Rocco', 'Ficção',1998, 1, 27.99, 200,3,'hp1.jpg');   
insert into livro values(00005,'Harry Potter e a Câmara', 'J.K Rolling', 'Rocco', 'Ficção',2000, 1, 27.99, 200,3,'hp2.jpg'); 
insert into livro values(00006,'Harry Potter e o Prisoneiro de Azkaban', 'J.K Rolling', 'Rocco', 'Ficção',2002, 1, 27.99, 200,3,'hp3.jpg'); 
insert into livro values(00007,'Harry Potter e o Cálice de Fogo', 'J.K Rolling', 'Rocco', 'Ficção',2004, 1, 27.99, 200,3,'h4.jpg'); 
insert into livro values(00008,'Harry Potter e a Ordem da Fênix', 'J.K Rolling', 'Rocco', 'Ficção',2006, 1, 27.99, 200,3,'hp5.jpg'); 
insert into livro values(00009,'Harry Potter e o Principe Mestiço ', 'J.K Rolling', 'Rocco', 'Ficção',2008, 1, 27.99, 200,3,'hp6.jpg'); 
insert into livro values(00010,'Harry Potter e as Relíquias da Morte ', 'J.K Rolling', 'Rocco', 'Ficção',2010, 1, 27.99, 200,3,'hp7.jpg'); 
insert into livro values(00011,'Harry Potter e as Relíquias da Morte ', 'J.K Rolling', 'Rocco', 'Ficção',2010, 1, 27.99, 200,3,'hp8.jpg');

insert into livro values(00012,'Como eu era antes de você ', 'Joye moes', 'Rocco', 'Romance',2011, 1,129.99, 200,3,'romance1.jpg');
insert into livro values(00013,'A culpa é das Estrelas', 'John Green', 'Rocco', 'Romance',2015, 1, 99.99, 200,3,'stars.jpg'); 




select*from livro;

