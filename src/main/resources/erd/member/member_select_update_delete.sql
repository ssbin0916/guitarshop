SELECT * FROM member;

SELECT * FROM member WHERE id = 1;

SELECT * FROM member WHERE login_id = 'testId';

UPDATE member SET
    login_id = 'updateId',
    password = 'updatePass',
    name = 'updateName',
    age = 29,
    phone = 'updatePhone',
    email = 'updateEmail',
    birth_date = TO_DATE('2222-12-22', 'YYYY-MM-DD'),
    address = 'updateAddress',
    gender = 'updateGendeer',
    role = 'ADMIN'
WHERE id = 1;

DELETE FROM member WHERE id = 1;