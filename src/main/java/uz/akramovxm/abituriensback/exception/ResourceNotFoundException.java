package uz.akramovxm.abituriensback.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, String field, Object value) {
        super(String.format("%s by %s '%s' not found", resourceName, field, value));
    }
}
