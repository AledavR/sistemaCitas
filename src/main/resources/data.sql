-- Roles por defecto
insert into roles(name) select 'ROLE_PATIENT' where not exists (select name from roles where name = 'ROLE_PATIENT');
insert into roles(name) select 'ROLE_ADMIN' where not exists (select name from roles where name = 'ROLE_ADMIN');
insert into roles(name) select 'ROLE_DOCTOR' where not exists (select name from roles where name = 'ROLE_DOCTOR');

-- Tipos de servicios medicos
insert into medical_service_types(type) select 'Cita' where not exists (select type from medical_service_types where type = 'Cita');
insert into medical_service_types(type) select 'Examen' where not exists (select type from medical_service_types where type = 'Examen');
insert into medical_service_types(type) select 'Operacion' where not exists (select type from medical_service_types where type = 'Operacion');

-- Usuario administrador por defecto (Hay un problema con el caracter ";" al trabajar con spring, se deben de crear dos statements)
if not exists (select 1 from users where email = 'soporte@leben.org' )
begin
insert into users(email, names, lastnames, phone, birthday, password) values ('soporte@leben.org', 'soporte', 'leben', '123456789', '06-12-1999', '$2a$10$M5wh8qzpJmM10PLp//X7YO99F0FX79eQRZgmfqgVkYHZpmJo62ypy')
end;

if not exists (select 1 from users_roles join users on users.id = users_roles.user_id join roles on roles.id = users_roles.role_id where users.email = 'soporte@leben.org' and roles.name = 'ROLE_ADMIN')
begin
insert into users_roles(user_id, role_id) select users.id,roles.id from users, roles where users.email = 'soporte@leben.org' and roles.name = 'ROLE_ADMIN'
end;
