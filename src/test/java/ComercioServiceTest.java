import com.placeti.avaliacao.dto.ComercioDTO;
import com.placeti.avaliacao.exceptions.ComercioNotFoundException;
import com.placeti.avaliacao.model.Cidade;
import com.placeti.avaliacao.model.Comercio;
import com.placeti.avaliacao.repository.ComercioRepository;
import com.placeti.avaliacao.service.ComercioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ComercioServiceTest {
    @Mock
    public ComercioRepository comercioRepository;

    @InjectMocks
    public ComercioService comercioService;

    @Test
    public void buscarComercioDeveRetornarDtoQuandoExiste()
    {
        Cidade c = new Cidade();
        Long id = 1L;
        Comercio comercio = new Comercio();
        comercio.setId(id);
        comercio.setNomeComercio("Comércio de Testes");
        comercio.setResponsavelComercio("Yasmin");
        comercio.setTipoComercio("Tech");
        comercio.setCidade(c);
        when(comercioRepository.findById(id))
                .thenReturn(Optional.of(comercio));
        ComercioDTO comercioDto = comercioService.buscarComercio(id);
        assertEquals(comercio.getId(), comercioDto.id());
        assertEquals(comercio.getNomeComercio(), comercioDto.nomeComercio());
        assertEquals(comercio.getResponsavelComercio(), comercioDto.responsavelComercio());
        assertEquals(comercio.getTipoComercio(), comercioDto.tipoComercio());

    }
    @Test
    void buscarComercioDeveLancarExcecaoQuandoNaoExiste()
    {
        Long id = 1L;
        when(comercioRepository.findById(id))
                .thenReturn(Optional.empty());
        assertThrows(ComercioNotFoundException.class, () -> comercioService.buscarComercio(id));
    }

}