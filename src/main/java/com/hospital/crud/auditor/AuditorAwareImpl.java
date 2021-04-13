package com.hospital.crud.auditor;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
		// Example User
        return Optional.of("CHRISTIAN");
    }
}
