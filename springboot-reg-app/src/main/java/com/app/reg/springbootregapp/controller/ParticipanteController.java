package com.app.reg.springbootregapp.controller;

//@RestController	
//@RequestMapping("/participantes")
public class ParticipanteController {
	
//	@Autowired
//	private ParticipanteRepository participanteRepository;
//
//	@GetMapping
//	public List<ParticipanteDTO> listar(){
//		 return ParticipanteDTO.converter(participanteRepository.findAll());
//	}
//
//	@PostMapping
//	@Transactional
//	public ResponseEntity<ParticipanteDTO> criar(@RequestBody @Valid ParticipanteForm form, UriComponentsBuilder builder){
//		Participante participante = form.converter();
//		participanteRepository.save(participante);
//		
//		URI uri = builder.path("/participantes/{id}").buildAndExpand(participante.getId()).toUri();
//				
//		return ResponseEntity.created(uri).body(new ParticipanteDTO(participante));
//	}
//	
//	@GetMapping("/{id}")
//	public ResponseEntity<DetalheParticipanteDTO> detalhar(@PathVariable("id") Long id){
//		Optional<Participante> optional = participanteRepository.findById(id);
//		
//		if(optional.isPresent()) {
//			return ResponseEntity.ok().body(new DetalheParticipanteDTO(optional.get()));
//		}
//		
//		return ResponseEntity.notFound().build();
//	}
//	
//	@PutMapping("/{id}")
//	@Transactional
//	public ResponseEntity<ParticipanteDTO> editar(@RequestBody @Valid EdicaoParticipanteForm form, @PathVariable("id") Long id){
//		Optional<Participante> optional = participanteRepository.findById(id);
//	
//		if(optional.isPresent()) {
//			Participante participante = optional.get();
//			form.editar(participante);
//			
//			return ResponseEntity.ok().body(new ParticipanteDTO(participante));
//		}
//		
//		return ResponseEntity.notFound().build();
//	}
//	
//	@DeleteMapping("/{id}")
//	@Transactional
//	public ResponseEntity<?> excluir(@PathVariable Long id){
//		Optional<Participante> optional = participanteRepository.findById(id);
//		
//		if(optional.isPresent()) {
//			participanteRepository.delete(optional.get());
//			
//			return ResponseEntity.ok().build();
//
//		}
//		return ResponseEntity.notFound().build();
//
//	}
	
}
