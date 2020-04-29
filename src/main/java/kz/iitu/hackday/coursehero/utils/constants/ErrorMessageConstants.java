package kz.iitu.hackday.coursehero.utils.constants;

public class ErrorMessageConstants {

    public interface MethodArgumentNotValid {
        String ERROR_CODE = "INVALID_ARGUMENT";
        String MESSAGE = "Аргументы метода некорректны. ";
    }

    public interface InternalServerError {
        String ERROR_CODE = "INTERNAL_SERVER_ERROR";
        String MESSAGE = "Системная ошибка сервера. Обратитесь к администратору системы.";
    }

    public interface InvalidArgumentData {
        String ERROR_CODE = "INVALID_ARGUMENT_DATA";
        String MESSAGE = "Аргументы метода некорректны.";
    }

    public interface DataNotFound {
        String ERROR_CODE = "DATA_NOT_FOUND";
        String MESSAGE = "Данные не найдены.";
    }

    public interface AccessDenied {
        String ERROR_CODE = "ACCESS_DENIED";
        String MESSAGE = "Отказ в доступе";
    }

    public interface WaitForMinute {
        String ERROR_CODE = "ACCESS_DENIED";
        String MESSAGE = "Частая попытка входа. Подождите до ";
    }

    public interface UnauthorizedError {
        String ERROR_CODE = "UNAUTHORIZED";
        String MESSAGE = "Не авторизован. Ошибка при валидации токена.";
    }
    public interface LockedError {
        String ERROR_CODE = "UNAUTHORIZED";
        String MESSAGE = "Ваш аккаунт заблокирован из за частых попыток входа";
    }
}

