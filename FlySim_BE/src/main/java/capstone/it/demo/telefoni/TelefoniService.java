package capstone.it.demo.telefoni;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TelefoniService {
    private final TelefoniRepository telefoniRepository;
    private final Cloudinary cloudinary;


    public Iterable<Telefoni> getAllTelefoni() {
        Iterable<Telefoni> telefoni = telefoniRepository.findAll();
        return telefoni;
    }

    public Optional<Telefoni> findById(Long id) {
        Optional<Telefoni> telefoni = telefoniRepository.findById(id);
        return telefoni;
    }

    public Telefoni createTelefoni(Telefoni telefoni) {
        Telefoni newTelefoni = telefoniRepository.save(telefoni);
        return newTelefoni;
    }
    public String uploadImage(MultipartFile file) throws IOException {
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return (String) uploadResult.get("secure_url");
    }
    public Telefoni createTelefoni(Telefoni telefoni, MultipartFile imageFile) throws IOException {
        String imageUrl = uploadImage(imageFile);
        telefoni.setImg(imageUrl);

        return telefoniRepository.save(telefoni);
        }



    public void deleteTelefoni(Long id) {
        telefoniRepository.deleteById(id);
    }
}
