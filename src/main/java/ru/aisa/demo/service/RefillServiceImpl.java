package ru.aisa.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import ru.aisa.demo.dto.RefillInputDto;
import ru.aisa.demo.dto.RefillOutputDto;
import ru.aisa.demo.entity.Refill;
import ru.aisa.demo.enums.RefillStatus;
import ru.aisa.demo.mapper.RefillMapper;
import ru.aisa.demo.repository.RefillRepository;

@Service
@RequiredArgsConstructor
@Log4j2
public class RefillServiceImpl implements RefillService {
    private final PlatformTransactionManager transactionManager;
    private final Queue<Refill, Integer> queue;
    private final RefillRepository repository;
    private final RefillMapper mapper;

    @Override
    public RefillOutputDto create(RefillInputDto dto) {
        Refill refill = mapper.dtoToRefill(dto);
        refill.setStatus(RefillStatus.WAITING);

        TransactionStatus transaction = transactionManager.getTransaction(null);
        try {
            repository.save(refill);
            transactionManager.commit(transaction);
        } catch (Exception e) {
            transactionManager.rollback(transaction);
            throw new RuntimeException("Failed to create refill");
        }

        try {
            queue.add(refill.getId());
        } catch (InterruptedException e){
            log.warn("Failed to add refill {} to queue", refill.getId());
            repository.delete(refill);
            throw new RuntimeException("Failed to create refill");
        }
        return mapper.refillToDto(refill);
    }

    @Override
    public RefillOutputDto get(Integer id) {
        Refill refill = repository.findById(id).orElseThrow();
        return mapper.refillToDto(refill);
    }

    @Override
    public void updateStatus(Refill refill, RefillStatus status) {
        refill.setStatus(status);
        repository.save(refill);
    }
}
