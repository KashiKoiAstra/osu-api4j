package kashi.koi.auth;

public interface TokenStore {
    Token load();
    void save(Token token);
    void clear();
}
