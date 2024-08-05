package com.kimikevin.nomad_backend.delegate;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DelegateService {
    private final DelegateRepository delegateRepository;

    public DelegateService(DelegateRepository delegateRepository) {
        this.delegateRepository = delegateRepository;
    }

    public List<Delegate> getAllDelegates() {
        return delegateRepository.findAll();
    }

    public Delegate getDelegateById(Long id) {
        return delegateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Delegate not found"));
    }

    public Delegate createDelegate(Delegate delegate) {
        return delegateRepository.save(delegate);
    }

    public Delegate updateDelegate(Long id, Delegate delegateDetails) {
        Delegate delegate = getDelegateById(id);

        delegate.setDelegateCity(delegateDetails.getDelegateCity());
        delegate.setDelegateFName(delegateDetails.getDelegateFName());
        delegate.setDelegateLName(delegateDetails.getDelegateLName());
        delegate.setDelegateState(delegateDetails.getDelegateState());
        delegate.setDelegateTitle(delegateDetails.getDelegateTitle());
        delegate.setDelegateStreet(delegateDetails.getDelegateStreet());
        delegate.setAttEmailAddress(delegateDetails.getAttEmailAddress());
        delegate.setAttFaxNo(delegateDetails.getAttFaxNo());
        delegate.setClientNo(delegateDetails.getClientNo());
        delegate.setDelegateZipCode(delegateDetails.getDelegateZipCode());

        return delegateRepository.save(delegate);
    }

    public void deleteDelegate(Long id) {
        delegateRepository.deleteById(id);
    }
}
