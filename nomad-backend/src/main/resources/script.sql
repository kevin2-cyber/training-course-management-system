-- Sequence for auto-incrementing primary keys
CREATE SEQUENCE seq_delegate_no START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_booking_no START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_course_no START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_course_fee_no START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_course_type_no START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_invoice_no START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_location_no START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_registration_no START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_employee_no START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_client_no START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_payment_method_no START WITH 1 INCREMENT BY 1;


-- Client table (added as it's referenced by Delegate)
CREATE TABLE Client (
    clientNo NUMBER PRIMARY KEY,
    clientName VARCHAR2(100) NOT NULL,
    clientAddress VARCHAR2(200),
    clientPhone VARCHAR2(20)
);

-- Employee table (added as it's referenced by multiple tables)
CREATE TABLE Employee (
    employeeNo NUMBER PRIMARY KEY,
    employeeName VARCHAR2(100) NOT NULL,
    employeeEmail VARCHAR2(100) UNIQUE,
    employeePhone VARCHAR2(20)
);

-- PaymentMethod table (added as it's referenced by Invoice)
CREATE TABLE PaymentMethod (
    pMethodNo NUMBER PRIMARY KEY,
    methodName VARCHAR2(50) NOT NULL
);

-- Delegate table
CREATE TABLE Delegate (
    delegateNo NUMBER PRIMARY KEY,
    delegateTitle VARCHAR2(10),
    delegateFName VARCHAR2(50) NOT NULL,
    delegateLName VARCHAR2(50) NOT NULL,
    delegateStreet VARCHAR2(100),
    delegateCity VARCHAR2(50),
    delegateState VARCHAR2(50),
    delegateZipCode VARCHAR2(20),
    attTelNo VARCHAR2(20),
    attFaxNo VARCHAR2(20),
    attEmailAddress VARCHAR2(100),
    clientNo NUMBER,
    CONSTRAINT fk_delegate_client FOREIGN KEY (clientNo) REFERENCES Client(clientNo)
);

-- Location table
CREATE TABLE Location (
    locationNo NUMBER PRIMARY KEY,
    locationName VARCHAR2(100) NOT NULL,
    maxSize NUMBER
);

-- CourseType table
CREATE TABLE CourseType (
    courseTypeNo NUMBER PRIMARY KEY,
    courseTypeDescription VARCHAR2(200) NOT NULL
);

-- Course table
CREATE TABLE Course (
    courseNo NUMBER PRIMARY KEY,
    courseName VARCHAR2(100) NOT NULL,
    courseDescription VARCHAR2(500),
    startDate DATE NOT NULL,
    startTime TIMESTAMP,
    endDate DATE NOT NULL,
    endTime TIMESTAMP,
    maxDelegates NUMBER,
    confirmed CHAR(1) CHECK (confirmed IN ('Y', 'N')),
    delivererEmployeeNo NUMBER,
    courseTypeNo NUMBER,
    CONSTRAINT fk_course_employee FOREIGN KEY (delivererEmployeeNo) REFERENCES Employee(employeeNo),
    CONSTRAINT fk_course_type FOREIGN KEY (courseTypeNo) REFERENCES CourseType(courseTypeNo)
);

-- Booking table
CREATE TABLE Booking (
    bookingNo NUMBER PRIMARY KEY,
    bookingDate DATE DEFAULT SYSDATE NOT NULL,
    locationNo NUMBER,
    courseNo NUMBER,
    bookingEmployeeNo NUMBER,
    CONSTRAINT fk_booking_location FOREIGN KEY (locationNo) REFERENCES Location(locationNo),
    CONSTRAINT fk_booking_course FOREIGN KEY (courseNo) REFERENCES Course(courseNo),
    CONSTRAINT fk_booking_employee FOREIGN KEY (bookingEmployeeNo) REFERENCES Employee(employeeNo)
);

-- CourseFee table
CREATE TABLE CourseFee (
    courseFeeNo NUMBER PRIMARY KEY,
    feeDescription VARCHAR2(200),
    fee NUMBER(10,2) NOT NULL,
    courseNo NUMBER,
    CONSTRAINT fk_course_fee_course FOREIGN KEY (courseNo) REFERENCES Course(courseNo)
);

-- Invoice table
CREATE TABLE Invoice (
    invoiceNo NUMBER PRIMARY KEY,
    dateRaised DATE DEFAULT SYSDATE NOT NULL,
    datePaid DATE,
    creditCardNo VARCHAR2(20),
    holdersName VARCHAR2(100),
    expiryDate DATE,
    registrationNo NUMBER,
    pMethodNo NUMBER,
    CONSTRAINT fk_invoice_registration FOREIGN KEY (registrationNo) REFERENCES Registration(registrationNo),
    CONSTRAINT fk_invoice_payment_method FOREIGN KEY (pMethodNo) REFERENCES PaymentMethod(pMethodNo)
);

-- Registration table
CREATE TABLE Registration (
    registrationNo NUMBER PRIMARY KEY,
    registrationDate DATE DEFAULT SYSDATE NOT NULL,
    delegateNo NUMBER,
    courseFeeNo NUMBER,
    registerEmployeeNo NUMBER,
    courseNo NUMBER,
    CONSTRAINT fk_registration_delegate FOREIGN KEY (delegateNo) REFERENCES Delegate(delegateNo),
    CONSTRAINT fk_registration_course_fee FOREIGN KEY (courseFeeNo) REFERENCES CourseFee(courseFeeNo),
    CONSTRAINT fk_registration_employee FOREIGN KEY (registerEmployeeNo) REFERENCES Employee(employeeNo),
    CONSTRAINT fk_registration_course FOREIGN KEY (courseNo) REFERENCES Course(courseNo)
);

-- Log table for user activities
CREATE TABLE UserActivityLog (
    logID NUMBER PRIMARY KEY,
    userID VARCHAR2(50),
    actionType VARCHAR2(20),
    tableName VARCHAR2(50),
    recordID NUMBER,
    actionDate TIMESTAMP DEFAULT SYSTIMESTAMP
);


-- ######### Additional Constraints #########
ALTER TABLE Course ADD CONSTRAINT chk_course_dates CHECK (endDate >= startDate);
ALTER TABLE Invoice ADD CONSTRAINT chk_invoice_dates CHECK (datePaid >= dateRaised);
ALTER TABLE CourseFee ADD CONSTRAINT chk_positive_fee CHECK (fee > 0);


-- ######### Triggers for Logging #########

-- Delegate Table Triggers
CREATE OR REPLACE TRIGGER trg_log_delegate_changes
AFTER INSERT OR UPDATE OR DELETE ON Delegate
FOR EACH ROW
DECLARE
    v_action VARCHAR2(10);
    v_record_id VARCHAR2(100);
    TYPE columns_t IS TABLE OF VARCHAR2(30);
    columnsArray columns_t := columns_t('delegateNo',
                                        'delegateTitle',
                                        'delegateFName',
                                        'delegateLName',
                                        'delegateStreet',
                                        'delegateCity',
                                        'delegateState',
                                        'delegateZipCode',
                                        'attTelNo',
                                        'attFaxNo',
                                        'attEmailAddress',
                                        'clientNo');
BEGIN
    IF INSERTING THEN
        v_action := 'INSERT';
    ELSIF UPDATING THEN
        v_action := 'UPDATE';
    ELSIF DELETING THEN
        v_action := 'DELETE';
    END IF;

    v_record_id := :NEW.delegateNo;

    IF INSERTING THEN
        INSERT INTO UserActivityLog (logID,
                                    userID,
                                    actionType,
                                    tableName,
                                    recordID,
                                    actionDate)
        VALUES (seq_log_id.NEXTVAL, USER, v_action, 'Delegate', v_record_id, SYSTIMESTAMP);
    END IF;

    IF UPDATING THEN
        FOR i IN 1..columnsArray.COUNT LOOP
            IF :OLD.&columnsArray(i) != :NEW.&columnsArray(i) OR (:OLD.&columnsArray(i) IS NULL AND :NEW.&columnsArray(i) IS NOT NULL) OR (:OLD.&columnsArray(i) IS NOT NULL AND :NEW.&columnsArray(i) IS NULL) THEN
                INSERT INTO UserActivityLog (logID,
                                            userID,
                                            actionType,
                                            tableName,
                                            recordID,
                                            columnName,
                                            oldValue,
                                            newValue,
                                            actionDate)
                VALUES (seq_log_id.NEXTVAL,
                        USER, v_action,
                        'Delegate',
                        v_record_id,
                        columnsArray(i),
                        :OLD.&columnsArray(i),
                        :NEW.&columnsArray(i),
                        SYSTIMESTAMP);
            END IF;
        END LOOP;
    END IF;

    IF DELETING THEN
        INSERT INTO UserActivityLog (logID,
                                    userID,
                                    actionType,
                                    tableName,
                                    recordID,
                                    actionDate)
        VALUES (seq_log_id.NEXTVAL,
                USER, v_action,
                'Delegate',
                v_record_id,
                SYSTIMESTAMP);
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error in trg_log_delegate_changes: ' || SQLERRM);
END;
/


-- Booking Table Triggers
CREATE OR REPLACE TRIGGER trg_log_booking_changes
AFTER INSERT OR UPDATE OR DELETE ON Booking
FOR EACH ROW
DECLARE
    v_action VARCHAR2(10);
    v_record_id VARCHAR2(100);
    TYPE columns_t IS TABLE OF VARCHAR2(30);
    columnsArray columns_t := columns_t('bookingNo',
                                        'bookingDate',
                                        'locationNo',
                                        'courseNo',
                                        'bookingEmployeeNo');
BEGIN
    IF INSERTING THEN
        v_action := 'INSERT';
    ELSIF UPDATING THEN
        v_action := 'UPDATE';
    ELSIF DELETING THEN
        v_action := 'DELETE';
    END IF;

    v_record_id := :NEW.bookingNo;

    IF INSERTING THEN
        INSERT INTO UserActivityLog (logID,
                                    userID,
                                    actionType,
                                    tableName,
                                    recordID,
                                    actionDate)
        VALUES (seq_log_id.NEXTVAL,
                USER, v_action,
                'Booking',
                v_record_id,
                SYSTIMESTAMP);
    END IF;

    IF UPDATING THEN
        FOR i IN 1..columnsArray.COUNT LOOP
            IF :OLD.&columnsArray(i) != :NEW.&columnsArray(i) OR (:OLD.&columnsArray(i) IS NULL AND :NEW.&columnsArray(i) IS NOT NULL) OR (:OLD.&columnsArray(i) IS NOT NULL AND :NEW.&columnsArray(i) IS NULL) THEN
                INSERT INTO UserActivityLog (logID,
                                            userID,
                                            actionType,
                                            tableName,
                                            recordID,
                                            columnName,
                                            oldValue,
                                            newValue,
                                            actionDate)
                VALUES (seq_log_id.NEXTVAL,
                        USER,
                        v_action,
                        'Booking',
                        v_record_id,
                        columnsArray(i),
                        :OLD.&columnsArray(i),
                        :NEW.&columnsArray(i),
                        SYSTIMESTAMP);
            END IF;
        END LOOP;
    END IF;

    IF DELETING THEN
        INSERT INTO UserActivityLog (logID,
                                    userID,
                                    actionType,
                                    tableName,
                                    recordID,
                                    actionDate)
        VALUES (seq_log_id.NEXTVAL,
                USER, v_action,
                'Booking',
                v_record_id,
                SYSTIMESTAMP);
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error in trg_log_booking_changes: ' || SQLERRM);
END;
/


-- Course Table Triggers
CREATE OR REPLACE TRIGGER trg_log_course_changes
AFTER INSERT OR UPDATE OR DELETE ON Course
FOR EACH ROW
DECLARE
    v_action VARCHAR2(10);
    v_record_id VARCHAR2(100);
    TYPE columns_t IS TABLE OF VARCHAR2(30);
    columnsArray columns_t := columns_t('courseNo',
                                        'courseName',
                                        'courseDescription',
                                        'startDate',
                                        'startTime',
                                        'endDate',
                                        'endTime',
                                        'maxDelegates',
                                        'confirmed',
                                        'delivererEmployeeNo',
                                        'courseTypeNo');
BEGIN
    IF INSERTING THEN
        v_action := 'INSERT';
    ELSIF UPDATING THEN
        v_action := 'UPDATE';
    ELSIF DELETING THEN
        v_action := 'DELETE';
    END IF;

    v_record_id := :NEW.courseNo;

    IF INSERTING THEN
        INSERT INTO UserActivityLog (logID,
                                    userID,
                                    actionType,
                                    tableName,
                                    recordID,
                                    actionDate)
        VALUES (seq_log_id.NEXTVAL,
                USER,
                v_action,
                'Course',
                v_record_id,
                SYSTIMESTAMP);
    END IF;

    IF UPDATING THEN
        FOR i IN 1..columnsArray.COUNT LOOP
            IF :OLD.&columnsArray(i) != :NEW.&columnsArray(i) OR (:OLD.&columnsArray(i) IS NULL AND :NEW.&columnsArray(i) IS NOT NULL) OR (:OLD.&columnsArray(i) IS NOT NULL AND :NEW.&columnsArray(i) IS NULL) THEN
                INSERT INTO UserActivityLog (logID,
                                            userID,
                                            actionType,
                                            tableName,
                                            recordID,
                                            columnName,
                                            oldValue,
                                            newValue,
                                            actionDate)
                VALUES (seq_log_id.NEXTVAL,
                        USER,
                        v_action,
                        'Course',
                        v_record_id,
                        columnsArray(i),
                        :OLD.&columnsArray(i),
                        :NEW.&columnsArray(i),
                        SYSTIMESTAMP);
            END IF;
        END LOOP;
    END IF;

    IF DELETING THEN
        INSERT INTO UserActivityLog (logID,
                                    userID,
                                    actionType,
                                    tableName,
                                    recordID,
                                    actionDate)
        VALUES (seq_log_id.NEXTVAL,
                USER,
                v_action,
                'Course',
                v_record_id,
                SYSTIMESTAMP);
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error in trg_log_course_changes: ' || SQLERRM);
END;
/


-- CourseFee Table Triggers
CREATE OR REPLACE TRIGGER trg_log_coursefee_changes
AFTER INSERT OR UPDATE OR DELETE ON CourseFee
FOR EACH ROW
DECLARE
    v_action VARCHAR2(10);
    v_record_id VARCHAR2(100);
    TYPE columns_t IS TABLE OF VARCHAR2(30);
    columnsArray columns_t := columns_t('courseFeeNo', 'feeDescription', 'fee', 'courseNo');
BEGIN
    IF INSERTING THEN
        v_action := 'INSERT';
    ELSIF UPDATING THEN
        v_action := 'UPDATE';
    ELSIF DELETING THEN
        v_action := 'DELETE';
    END IF;

    v_record_id := :NEW.courseFeeNo;

    IF INSERTING THEN
        INSERT INTO UserActivityLog (logID, userID, actionType, tableName, recordID, actionDate)
        VALUES (seq_log_id.NEXTVAL, USER, v_action, 'CourseFee', v_record_id, SYSTIMESTAMP);
    END IF;

    IF UPDATING THEN
        FOR i IN 1..columnsArray.COUNT LOOP
            IF :OLD.&columnsArray(i) != :NEW.&columnsArray(i) OR (:OLD.&columnsArray(i) IS NULL AND :NEW.&columnsArray(i) IS NOT NULL) OR (:OLD.&columnsArray(i) IS NOT NULL AND :NEW.&columnsArray(i) IS NULL) THEN
                INSERT INTO UserActivityLog (logID,
                                            userID,
                                            actionType,
                                            tableName,
                                            recordID,
                                            columnName,
                                            oldValue,
                                            newValue,
                                            actionDate)
                VALUES (seq_log_id.NEXTVAL,
                        USER,
                        v_action,
                        'CourseFee',
                        v_record_id,
                        columnsArray(i),
                        :OLD.&columnsArray(i),
                        :NEW.&columnsArray(i),
                        SYSTIMESTAMP);
            END IF;
        END LOOP;
    END IF;

    IF DELETING THEN
        INSERT INTO UserActivityLog (logID,
                                    userID,
                                    actionType,
                                    tableName,
                                    recordID,
                                    actionDate)
        VALUES (seq_log_id.NEXTVAL,
                USER,
                v_action,
                'CourseFee',
                v_record_id,
                SYSTIMESTAMP);
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error in trg_log_coursefee_changes: ' || SQLERRM);
END;
/


-- Invoice Table Triggers
CREATE OR REPLACE TRIGGER trg_log_invoice_changes
AFTER INSERT OR UPDATE OR DELETE ON Invoice
FOR EACH ROW
DECLARE
    v_action VARCHAR2(10);
    v_record_id VARCHAR2(100);
    TYPE columns_t IS TABLE OF VARCHAR2(30);
    columnsArray columns_t := columns_t('invoiceNo',
                                        'dateRaised',
                                        'datePaid',
                                        'creditCardNo',
                                        'holdersName',
                                        'expiryDate',
                                        'registrationNo',
                                        'pMethodNo');
BEGIN
    IF INSERTING THEN
        v_action := 'INSERT';
    ELSIF UPDATING THEN
        v_action := 'UPDATE';
    ELSIF DELETING THEN
        v_action := 'DELETE';
    END IF;

    v_record_id := :NEW.invoiceNo;

    IF INSERTING THEN
        INSERT INTO UserActivityLog (logID,
                                    userID,
                                    actionType,
                                    tableName,
                                    recordID,
                                    actionDate)
        VALUES (seq_log_id.NEXTVAL,
                USER,
                v_action,
                'Invoice',
                v_record_id,
                SYSTIMESTAMP);
    END IF;

    IF UPDATING THEN
        FOR i IN 1..columnsArray.COUNT LOOP
            IF :OLD.&columnsArray(i) != :NEW.&columnsArray(i) OR (:OLD.&columnsArray(i) IS NULL AND :NEW.&columnsArray(i) IS NOT NULL) OR (:OLD.&columnsArray(i) IS NOT NULL AND :NEW.&columnsArray(i) IS NULL) THEN
                INSERT INTO UserActivityLog (logID,
                                            userID,
                                            actionType,
                                            tableName,
                                            recordID,
                                            columnName,
                                            oldValue,
                                            newValue,
                                            actionDate)
                VALUES (seq_log_id.NEXTVAL,
                        USER,
                        v_action,
                        'Invoice',
                        v_record_id,
                        columnsArray(i),
                        :OLD.&columnsArray(i),
                        :NEW.&columnsArray(i),
                        SYSTIMESTAMP);
            END IF;
        END LOOP;
    END IF;

    IF DELETING THEN
        INSERT INTO UserActivityLog (logID,
                                    userID,
                                    actionType,
                                    tableName,
                                    recordID,
                                    actionDate)
        VALUES (seq_log_id.NEXTVAL,
                USER,
                v_action,
                'Invoice',
                v_record_id,
                SYSTIMESTAMP);
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error in trg_log_invoice_changes: ' || SQLERRM);
END;
/


-- Registration Table Triggers
CREATE OR REPLACE TRIGGER trg_log_registration_changes
AFTER INSERT OR UPDATE OR DELETE ON Registration
FOR EACH ROW
DECLARE
    v_action VARCHAR2(10);
    v_record_id VARCHAR2(100);
    TYPE columns_t IS TABLE OF VARCHAR2(30);
    columnsArray columns_t := columns_t('registrationNo',
                                        'registrationDate',
                                        'delegateNo',
                                        'courseFeeNo',
                                        'registerEmployeeNo',
                                        'courseNo');
BEGIN
    IF INSERTING THEN
        v_action := 'INSERT';
    ELSIF UPDATING THEN
        v_action := 'UPDATE';
    ELSIF DELETING THEN
        v_action := 'DELETE';
    END IF;

    v_record_id := :NEW.registrationNo;

    IF INSERTING THEN
        INSERT INTO UserActivityLog (logID,
                                    userID,
                                    actionType,
                                    tableName,
                                    recordID,
                                    actionDate)
        VALUES (seq_log_id.NEXTVAL,
                USER,
                v_action,
                'Registration',
                v_record_id,
                SYSTIMESTAMP);
    END IF;

    IF UPDATING THEN
        FOR i IN 1..columnsArray.COUNT LOOP
            IF :OLD.&columnsArray(i) != :NEW.&columnsArray(i) OR (:OLD.&columnsArray(i) IS NULL AND :NEW.&columnsArray(i) IS NOT NULL) OR (:OLD.&columnsArray(i) IS NOT NULL AND :NEW.&columnsArray(i) IS NULL) THEN
                INSERT INTO UserActivityLog (logID,
                                            userID,
                                            actionType,
                                            tableName,
                                            recordID,
                                            columnName,
                                            oldValue,
                                            newValue,
                                            actionDate)
                VALUES (seq_log_id.NEXTVAL,
                        USER,
                        v_action,
                        'Registration',
                        v_record_id,
                        columnsArray(i),
                        :OLD.&columnsArray(i),
                        :NEW.&columnsArray(i),
                        SYSTIMESTAMP);
            END IF;
        END LOOP;
    END IF;

    IF DELETING THEN
        INSERT INTO UserActivityLog (logID,
                                    userID,
                                    actionType,
                                    tableName,
                                    recordID,
                                    actionDate)
        VALUES (seq_log_id.NEXTVAL,
                USER,
                v_action,
                'Registration',
                v_record_id,
                SYSTIMESTAMP);
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error in trg_log_registration_changes: ' || SQLERRM);
END;
/


-- ######### Stored Procedures #########
-- Insert procedure
CREATE OR REPLACE PROCEDURE sp_insert_course (
    p_courseName IN VARCHAR2,
    p_courseDescription IN VARCHAR2,
    p_startDate IN DATE,
    p_endDate IN DATE,
    p_maxDelegates IN NUMBER,
    p_delivererEmployeeNo IN NUMBER,
    p_courseTypeNo IN NUMBER
)
IS
BEGIN
    INSERT INTO Course (courseNo,
                        courseName,
                        courseDescription,
                        startDate,
                        endDate,
                        maxDelegates,
                        delivererEmployeeNo,
                        courseTypeNo)
    VALUES (seq_course_no.NEXTVAL,
            p_courseName,
            p_courseDescription,
            p_startDate,
            p_endDate,
            p_maxDelegates,
            p_delivererEmployeeNo,
            p_courseTypeNo);
    
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20001, 'Error inserting course: ' || SQLERRM);
END;
/

-- Update procedure
CREATE OR REPLACE PROCEDURE sp_update_course (
    p_courseNo IN NUMBER,
    p_courseName IN VARCHAR2,
    p_courseDescription IN VARCHAR2,
    p_startDate IN DATE,
    p_endDate IN DATE,
    p_maxDelegates IN NUMBER,
    p_delivererEmployeeNo IN NUMBER,
    p_courseTypeNo IN NUMBER
)
IS
BEGIN
    UPDATE Course
    SET courseName = p_courseName,
        courseDescription = p_courseDescription,
        startDate = p_startDate,
        endDate = p_endDate,
        maxDelegates = p_maxDelegates,
        delivererEmployeeNo = p_delivererEmployeeNo,
        courseTypeNo = p_courseTypeNo
    WHERE courseNo = p_courseNo;
    
    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'No course found with the given courseNo');
    END IF;
    
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20003, 'Error updating course: ' || SQLERRM);
END;
/

-- Delete procedure
CREATE OR REPLACE PROCEDURE sp_delete_course (
    p_courseNo IN NUMBER
)
IS
BEGIN
    DELETE FROM Course WHERE courseNo = p_courseNo;
    
    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20004, 'No course found with the given courseNo');
    END IF;
    
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20005, 'Error deleting course: ' || SQLERRM);
END;
/


-- ######### Backup Procedure #########
CREATE OR REPLACE PROCEDURE sp_backup_database (
    p_directory IN VARCHAR2,
    p_filename IN VARCHAR2
)
IS
    v_backup_cmd VARCHAR2(1000);
BEGIN
    v_backup_cmd := 'expdp system/password@XE DIRECTORY=' || p_directory || ' DUMPFILE=' || p_filename || ' FULL=Y';
    
    -- Execute the backup command
    DBMS_SCHEDULER.CREATE_JOB (
        job_name        => 'BACKUP_JOB',
        job_type        => 'EXECUTABLE',
        job_action      => '/bin/sh',
        number_of_arguments => 3,
        start_date      => SYSTIMESTAMP,
        enabled         => TRUE,
        auto_drop       => TRUE
    );

    DBMS_SCHEDULER.SET_JOB_ARGUMENT_VALUE('BACKUP_JOB', 1, '-c');
    DBMS_SCHEDULER.SET_JOB_ARGUMENT_VALUE('BACKUP_JOB', 2, v_backup_cmd);
    DBMS_SCHEDULER.SET_JOB_ARGUMENT_VALUE('BACKUP_JOB', 3, '');

    DBMS_SCHEDULER.ENABLE('BACKUP_JOB');
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20006, 'Error backing up database: ' || SQLERRM);
END;
/
