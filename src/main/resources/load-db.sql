--insert into produto(id, nome) values(1,'Teste');


-- INSERT INTO fruit(id, name, color) VALUES (hibernate_sequence.nextval, 'Cherry', 'Red');
-- INSERT INTO fruit(id, name, color) VALUES (hibernate_sequence.nextval, 'Apple', 'Red');
-- INSERT INTO fruit(id, name, color) VALUES (hibernate_sequence.nextval, 'Banana', 'Yellow');
-- INSERT INTO fruit(id, name, color) VALUES (hibernate_sequence.nextval, 'Avocado', 'Green');
-- INSERT INTO fruit(id, name, color) VALUES (hibernate_sequence.nextval, 'Strawberry', 'Red');

insert into estado(uf, nome, selecionado) values('RS','Rio Grande do Sul', false);
insert into cidade(id, populacao, nome, uf) values(hibernate_sequence.nextval,50,'Esteio','RS');
insert into cidade(id, populacao, nome, uf) values(hibernate_sequence.nextval,100,'Sapucaia do Sul','RS');

insert into estado(uf, nome, selecionado) values('SC','Santa Catarina', true);
insert into cidade(id, populacao, nome, uf) values(hibernate_sequence.nextval,200,'Brusque','SC');
insert into cidade(id, populacao, nome, uf) values(hibernate_sequence.nextval,300,'Florianópolis','SC');

insert into estado(uf, nome, selecionado) values('PR','Paraná', false);
insert into cidade(id, populacao, nome, uf) values(hibernate_sequence.nextval,1000,'Ponta Grossa','PR');
insert into cidade(id, populacao, nome, uf) values(hibernate_sequence.nextval,2000,'Curitiba','PR');