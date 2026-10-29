package com.fitlog_ai.fitlog.progress.service;

import com.fitlog_ai.fitlog.auth.entity.User;
import com.fitlog_ai.fitlog.auth.repository.UserRepository;
import com.fitlog_ai.fitlog.common.exception.ResourceNotFoundException;
import com.fitlog_ai.fitlog.progress.dto.BodyMeasurementRequest;
import com.fitlog_ai.fitlog.progress.dto.BodyMeasurementResponse;
import com.fitlog_ai.fitlog.progress.entity.BodyMeasurement;
import com.fitlog_ai.fitlog.progress.mapper.BodyMeasurementMapper;
import com.fitlog_ai.fitlog.progress.repository.BodyMeasurementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BodyMeasurementServiceImpl implements BodyMeasurementService {

    private final BodyMeasurementRepository bodyMeasurementRepository;
    private final BodyMeasurementMapper bodyMeasurementMapper;
    private final UserRepository userRepository;


    public BodyMeasurementServiceImpl(BodyMeasurementRepository bodyMeasurementRepository, BodyMeasurementMapper bodyMeasurementMapper, UserRepository userRepository) {
        this.bodyMeasurementRepository = bodyMeasurementRepository;
        this.bodyMeasurementMapper = bodyMeasurementMapper;
        this.userRepository = userRepository;
    }

    @Override
    public BodyMeasurementResponse getBodyMeasurementById(Long id) {
        return bodyMeasurementMapper.toResponse(
                bodyMeasurementRepository.findById(id).
                        orElseThrow(()-> new ResourceNotFoundException("BodyMeasurement with id " + id + " not found")));
    }

    @Override
    public BodyMeasurementResponse createBodyMeasurement(Long userId, BodyMeasurementRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User with id " + userId + " not found"));

        BodyMeasurement bodyMeasurement = bodyMeasurementMapper.toEntity(request);
        bodyMeasurement.setUser(user);
        BodyMeasurement savedbodyMeasurement = bodyMeasurementRepository.save(bodyMeasurement);
        return bodyMeasurementMapper.toResponse(savedbodyMeasurement);
    }

    @Override
    public BodyMeasurementResponse updateBodyMeasurement(Long id, BodyMeasurementRequest request) {
        BodyMeasurement bodyMeasurement = bodyMeasurementRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Body measurement with id " + id + " not found"
                        ));

        bodyMeasurement.setDate(request.date());
        bodyMeasurement.setWeightKg(request.weightKg());
        bodyMeasurement.setBodyFatPercent(request.bodyFatPercent());
        bodyMeasurement.setNeckCm(request.neckCm());
        bodyMeasurement.setChestCm(request.chestCm());
        bodyMeasurement.setWaistCm(request.waistCm());
        bodyMeasurement.setChestCm(request.chestCm());
        bodyMeasurement.setBicepsCm(request.bicepsCm());
        bodyMeasurement.setForearmCm(request.forearmCm());
        bodyMeasurement.setThighCm(request.thighCm());
        bodyMeasurement.setCalfCm(request.calfCm());
        bodyMeasurement.setNotes(request.notes());

        BodyMeasurement saved = bodyMeasurementRepository.save(bodyMeasurement);

        return bodyMeasurementMapper.toResponse(saved);
    }

    @Override
    public void deleteBodyMeasurement(Long id) {
        BodyMeasurement bodyMeasurement = bodyMeasurementRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("BodyMeasurement with id" + id + " not found"));
        bodyMeasurementRepository.delete(bodyMeasurement);
    }

    @Override
    public List<BodyMeasurementResponse> getByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User with id " + userId + " not found");
        }

        return bodyMeasurementRepository.findByUserIdOrderByDateDesc(userId)
                .stream()
                .map(bodyMeasurementMapper::toResponse)
                .toList();
    }
}
