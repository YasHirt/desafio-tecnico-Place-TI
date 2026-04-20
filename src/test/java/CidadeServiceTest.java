import com.placeti.avaliacao.dto.CidadeDTO;
import com.placeti.avaliacao.exceptions.CityNotFoundException;
import com.placeti.avaliacao.model.Cidade;
import com.placeti.avaliacao.repository.CidadeRepository;
import com.placeti.avaliacao.service.CidadeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

    @ExtendWith(MockitoExtension.class)
    class CidadeServiceTest {

        @Mock
        private CidadeRepository cidadeRepository;

        @InjectMocks
        private CidadeService cidadeService;

        @Test
        void deveRetornarCidadeDTOQuandoIdValido() {
            Long id = 1L;
            Cidade cidade = new Cidade();
            cidade.setId(id);
            cidade.setNome("Brasília");
            cidade.setUf("DF");
            cidade.setCapital(true);

            when(cidadeRepository.findById(id))
                    .thenReturn(Optional.of(cidade));

            CidadeDTO dto = cidadeService.pesquisarCidade(id);

            assertNotNull(dto);
            assertEquals(id, dto.id());
            assertEquals("Brasília", dto.nome());
            assertEquals("DF", dto.uf());
            assertTrue(dto.capital());
        }
        @Test
        void deveRetornarCityNotFoundExceptionQuandoCidadeNaoExiste()
        {
            Long id = 2L;
            when(cidadeRepository.findById(id))
                    .thenReturn(Optional.empty());
            assertThrows(CityNotFoundException.class, () -> cidadeService.pesquisarCidade(2L));
        }
        @Test
        void lancaIllegalArgumentExceptionSeIdNaoNuloNaInclusao()
        {
            CidadeDTO cidadeDTO = new CidadeDTO(1L, "Brasília", "DF", true);
            assertThrows(IllegalArgumentException.class, () -> cidadeService.incluirCidade(cidadeDTO));
        }
        @Test
        void alterarCidadeLancaIllegalArgumentExceptionSeIdNulo()
        {
            //passa mesmo se id nao null
            CidadeDTO cidadeDTO = new CidadeDTO(null, "Brasília", "DF", true);
            assertThrows(IllegalArgumentException.class, () -> cidadeService.alterarCidade(cidadeDTO));
        }
        //excluirCidade lanca exceção quando cidade não existe
        @Test
        void excluirCidadeLancaCityNotFoundExceptionQuandoCidadeNaoExiste()
        {
            Long id = 2L;
            when(cidadeRepository.findById(id))
                    .thenReturn(Optional.empty());
            assertThrows(CityNotFoundException.class, () -> cidadeService.excluirCidade(2L));
        }
        @Test
        void deveIncluirCidadeQuandoDtoValida()
        {

            CidadeDTO cidadeEntrada= new CidadeDTO(null,"Brasília", "DF", true);
            cidadeService.incluirCidade(cidadeEntrada);

            verify(cidadeRepository).save(any(Cidade.class));
        }
        }

