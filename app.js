// Fashion Peak - Spring Boot
// GA7-220501096-AA3-EV01

function filtrar(cat, btn) {
  document.querySelectorAll('.filtro-btn').forEach(b => b.classList.remove('activo'));
  btn.classList.add('activo');
  document.querySelectorAll('.producto-card').forEach(c => {
    c.style.display = (cat === 'todos' || c.dataset.categoria === cat) ? 'block' : 'none';
  });
}
