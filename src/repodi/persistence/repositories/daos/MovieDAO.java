package repodi.persistence.repositories.daos;

import org.jetbrains.annotations.NotNull;
import repodi.persistence.exceptions.MovieNotFoundException;
import repodi.persistence.beans.Movie;

import java.util.List;

public interface MovieDAO {

    /**
     * Adiciona um filme na base de dados.
     * @param movie filme para ser adicionado.
     * @throws Exception caso ocorra algum problema durante a operação.
     */
    void add(@NotNull Movie movie) throws Exception;

    /**
     * Atualiza um filme na base de dados.
     * @param movie filme para ser atualizado.
     * @throws Exception caso ocorra algum problema durante a operação.
     */
    void update(@NotNull Movie movie) throws Exception;

    /**
     * Retorna uma lista contendo todos os filmes existentes na base de dados.
     * @return lista contendo todos os filmes armazenados.
     */
    List<Movie> selectAll() throws MovieNotFoundException;

    /**
     * Retorna o filme que possui o uuid informado. Caso ele não exista ou ocorrer algum erro, retorna
     * a exceção MovieNotFoundException.
     * @param uuid identificador do filme que se deseja buscar.
     * @return filme que possui o uuid informado.
     * @throws MovieNotFoundException se não existir um filme com o uuid informado
     * ou se um erro ocorrer durante a busca.
     */
    Movie select(@NotNull String uuid) throws MovieNotFoundException;

    /**
     * Limpa a lista de filmes da base de dados, excluindo todos os filmes existentes.
     * @throws Exception caso ocorra algum problema durante a operação.
     */
    void clearAll() throws Exception;
}
