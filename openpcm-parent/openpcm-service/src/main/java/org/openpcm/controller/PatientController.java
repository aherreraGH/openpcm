package org.openpcm.controller;

import java.util.List;

import org.openpcm.exceptions.DataViolationException;
import org.openpcm.exceptions.NotFoundException;
import org.openpcm.model.Patient;
import org.openpcm.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="/api/v1/patient")
@RequestMapping("/api/v1/patient")
public class PatientController extends BaseController{

	private final PatientService patientService;
	
	@Autowired
	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="createPatient", response=Patient.class)
	@RequestMapping(value ="", method=RequestMethod.POST)
	public @ResponseBody Patient createPatient(@RequestBody Patient patient) throws DataViolationException {
		return patientService.create(patient);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value="readPatients", response=Patient.class, responseContainer="List")
	@RequestMapping(value ="", method=RequestMethod.GET)
	public @ResponseBody List<Patient> readPatients() {
		return patientService.readAll();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value="updatePatient", response=Patient.class)
	@PutMapping(value="/{id}")
	public @ResponseBody Patient readPatient(@PathVariable Long id, @RequestBody Patient patient) throws NotFoundException {
		return patientService.update(id, patient);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value="readPatient", response=Patient.class)
	@GetMapping(value="/{id}")
	public @ResponseBody Patient readPatient(@PathVariable Long id) throws NotFoundException {
		return patientService.read(id);
	}
	
	@RequestMapping(value ="/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value="deletePatient")
	public void deletePatient(@PathVariable Long id) {
		patientService.delete(id);
	}
}
