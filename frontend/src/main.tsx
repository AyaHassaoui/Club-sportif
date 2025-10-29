import React, { useState, useEffect } from 'react'
import ReactDOM from 'react-dom/client'
import { BrowserRouter, Link, Route, Routes } from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.min.css'
import './index.css'
import { FilterCard } from './components/FilterCard'
import { ActiviteFilters } from './components/ActiviteFilters'
import { MembreFilters } from './components/MembreFilters'
import { ActiviteCard } from './components/ActiviteCard'
import { MembreCard } from './components/MembreCard'

// Types
interface Activite {
  id: number
  nom: string
  niveau: string
  capacite: number
}

interface Membre {
  id: number
  nom: string
  categorie: string
  dateAdhesion: string
}

interface Inscription {
  id: number
  dateInscription: string
  montant: number
  statut: string
  activite: Activite
  membre: Membre
}

// API functions
const api = {
  async get<T>(url: string): Promise<T> {
    const response = await fetch(`/api${url}`)
    if (!response.ok) throw new Error('Failed to fetch')
    return response.json()
  }
}

function Home() {
  return (
    <div className="container py-4">
      <h1 className="mb-3">Club Sportif</h1>
      <p>Gestion des activités, membres et inscriptions du club sportif.</p>
      <div className="d-flex gap-2">
        <Link to="/activites" className="btn btn-primary">Activités</Link>
        <Link to="/membres" className="btn btn-secondary">Membres</Link>
        <Link to="/inscriptions" className="btn btn-success">Inscriptions</Link>
      </div>
    </div>
  )
}

function ActivitesList() {
  const [activites, setActivites] = useState<Activite[]>([])
  const [loading, setLoading] = useState(true)
  const [filters, setFilters] = useState({
    niveau: '',
    nom: '',
    capaciteMin: '',
    capaciteMax: ''
  })

  useEffect(() => {
    loadActivites()
  }, [filters])

  const loadActivites = async () => {
    try {
      setLoading(true)
      const params = new URLSearchParams()
      
      if (filters.niveau) params.append('niveau', filters.niveau)
      if (filters.nom) params.append('nom', filters.nom)
      if (filters.capaciteMin) params.append('capaciteMin', filters.capaciteMin)
      if (filters.capaciteMax) params.append('capaciteMax', filters.capaciteMax)
      
      const url = `/activites${params.toString() ? '?' + params.toString() : ''}`
      const data = await api.get<Activite[]>(url)
      setActivites(data)
    } catch (error) {
      console.error('Error loading activites:', error)
    } finally {
      setLoading(false)
    }
  }

  const handleFilterChange = (key: string, value: string) => {
    setFilters(prev => ({ ...prev, [key]: value }))
  }

  const clearFilters = () => {
    setFilters({
      niveau: '',
      nom: '',
      capaciteMin: '',
      capaciteMax: ''
    })
  }

  if (loading) return <div className="container py-4">Chargement...</div>

  return (
    <div className="container py-4">
      <h2>Activités</h2>
      
      <FilterCard 
        title="Filtres"
        onClear={clearFilters}
        resultCount={activites.length}
        resultLabel="activité"
      >
        <ActiviteFilters 
          filters={filters}
          onFilterChange={handleFilterChange}
        />
      </FilterCard>
      
      <div className="row">
        {activites.map(activite => (
          <ActiviteCard key={activite.id} activite={activite} />
        ))}
      </div>
      
      {activites.length === 0 && (
        <div className="text-center py-5">
          <p className="text-muted">Aucune activité trouvée avec ces critères.</p>
        </div>
      )}
    </div>
  )
}

function MembresList() {
  const [membres, setMembres] = useState<Membre[]>([])
  const [loading, setLoading] = useState(true)
  const [filters, setFilters] = useState({
    categorie: '',
    nom: '',
    dateDebut: '',
    dateFin: ''
  })

  useEffect(() => {
    loadMembres()
  }, [filters])

  const loadMembres = async () => {
    try {
      setLoading(true)
      const params = new URLSearchParams()
      
      if (filters.categorie) params.append('categorie', filters.categorie)
      if (filters.nom) params.append('nom', filters.nom)
      if (filters.dateDebut) params.append('dateDebut', filters.dateDebut)
      if (filters.dateFin) params.append('dateFin', filters.dateFin)
      
      const url = `/membres${params.toString() ? '?' + params.toString() : ''}`
      const data = await api.get<Membre[]>(url)
      setMembres(data)
    } catch (error) {
      console.error('Error loading membres:', error)
    } finally {
      setLoading(false)
    }
  }

  const handleFilterChange = (key: string, value: string) => {
    setFilters(prev => ({ ...prev, [key]: value }))
  }

  const clearFilters = () => {
    setFilters({
      categorie: '',
      nom: '',
      dateDebut: '',
      dateFin: ''
    })
  }

  if (loading) return <div className="container py-4">Chargement...</div>

  return (
    <div className="container py-4">
      <h2>Membres</h2>
      
      <FilterCard 
        title="Filtres"
        onClear={clearFilters}
        resultCount={membres.length}
        resultLabel="membre"
      >
        <MembreFilters 
          filters={filters}
          onFilterChange={handleFilterChange}
        />
      </FilterCard>
      
      <div className="row">
        {membres.map(membre => (
          <MembreCard key={membre.id} membre={membre} />
        ))}
      </div>
      
      {membres.length === 0 && (
        <div className="text-center py-5">
          <p className="text-muted">Aucun membre trouvé avec ces critères.</p>
        </div>
      )}
    </div>
  )
}

function InscriptionsList() {
  const [inscriptions, setInscriptions] = useState<Inscription[]>([])
  const [loading, setLoading] = useState(true)
  const [filters, setFilters] = useState({
    statut: '',
    dateDebut: '',
    dateFin: '',
    montantMin: '',
    montantMax: ''
  })

  useEffect(() => {
    loadInscriptions()
  }, [filters])

  const loadInscriptions = async () => {
    try {
      setLoading(true)
      const params = new URLSearchParams()
      
      if (filters.dateDebut) params.append('start', filters.dateDebut)
      if (filters.dateFin) params.append('end', filters.dateFin)
      
      const url = `/inscriptions${params.toString() ? '?' + params.toString() : ''}`
      let data = await api.get<Inscription[]>(url)
      
      // Filtrage côté client pour les autres critères
      if (filters.statut) {
        data = data.filter(i => i.statut.toLowerCase() === filters.statut.toLowerCase())
      }
      if (filters.montantMin) {
        data = data.filter(i => i.montant >= parseFloat(filters.montantMin))
      }
      if (filters.montantMax) {
        data = data.filter(i => i.montant <= parseFloat(filters.montantMax))
      }
      
      setInscriptions(data)
    } catch (error) {
      console.error('Error loading inscriptions:', error)
    } finally {
      setLoading(false)
    }
  }

  const handleFilterChange = (key: string, value: string) => {
    setFilters(prev => ({ ...prev, [key]: value }))
  }

  const clearFilters = () => {
    setFilters({
      statut: '',
      dateDebut: '',
      dateFin: '',
      montantMin: '',
      montantMax: ''
    })
  }

  if (loading) return <div className="container py-4">Chargement...</div>

  return (
    <div className="container py-4">
      <h2>Inscriptions</h2>
      
      {/* Filtres */}
      <div className="card mb-4">
        <div className="card-header">
          <h5 className="mb-0">Filtres</h5>
        </div>
        <div className="card-body">
          <div className="row g-3">
            <div className="col-md-2">
              <label className="form-label">Statut</label>
              <select 
                className="form-select" 
                value={filters.statut} 
                onChange={(e) => handleFilterChange('statut', e.target.value)}
              >
                <option value="">Tous les statuts</option>
                <option value="Confirmée">Confirmée</option>
                <option value="En attente">En attente</option>
                <option value="Annulée">Annulée</option>
              </select>
            </div>
            
            <div className="col-md-2">
              <label className="form-label">Date début</label>
              <input 
                type="date" 
                className="form-control" 
                value={filters.dateDebut}
                onChange={(e) => handleFilterChange('dateDebut', e.target.value)}
              />
            </div>
            
            <div className="col-md-2">
              <label className="form-label">Date fin</label>
              <input 
                type="date" 
                className="form-control" 
                value={filters.dateFin}
                onChange={(e) => handleFilterChange('dateFin', e.target.value)}
              />
            </div>
            
            <div className="col-md-2">
              <label className="form-label">Montant min</label>
              <input 
                type="number" 
                className="form-control" 
                placeholder="Min"
                value={filters.montantMin}
                onChange={(e) => handleFilterChange('montantMin', e.target.value)}
              />
            </div>
            
            <div className="col-md-2">
              <label className="form-label">Montant max</label>
              <input 
                type="number" 
                className="form-control" 
                placeholder="Max"
                value={filters.montantMax}
                onChange={(e) => handleFilterChange('montantMax', e.target.value)}
              />
            </div>
            
            <div className="col-md-2 d-flex align-items-end">
              <button 
                className="btn btn-outline-secondary w-100" 
                onClick={clearFilters}
              >
                Effacer
              </button>
            </div>
          </div>
        </div>
      </div>

      {/* Résultats */}
      <div className="d-flex justify-content-between align-items-center mb-3">
        <span className="text-muted">{inscriptions.length} inscription(s) trouvée(s)</span>
      </div>
      
      <div className="table-responsive">
        <table className="table table-striped">
          <thead>
            <tr>
              <th>Membre</th>
              <th>Activité</th>
              <th>Date</th>
              <th>Montant</th>
              <th>Statut</th>
            </tr>
          </thead>
          <tbody>
            {inscriptions.map(inscription => (
              <tr key={inscription.id}>
                <td>{inscription.membre.nom}</td>
                <td>
                  <span className="badge bg-secondary me-1">{inscription.activite.niveau}</span>
                  {inscription.activite.nom}
                </td>
                <td>{new Date(inscription.dateInscription).toLocaleDateString()}</td>
                <td><strong>{inscription.montant}€</strong></td>
                <td>
                  <span className={`badge ${
                    inscription.statut === 'Confirmée' ? 'bg-success' : 
                    inscription.statut === 'En attente' ? 'bg-warning' : 'bg-danger'
                  }`}>
                    {inscription.statut}
                  </span>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      
      {inscriptions.length === 0 && (
        <div className="text-center py-5">
          <p className="text-muted">Aucune inscription trouvée avec ces critères.</p>
        </div>
      )}
    </div>
  )
}

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/activites" element={<ActivitesList />} />
        <Route path="/membres" element={<MembresList />} />
        <Route path="/inscriptions" element={<InscriptionsList />} />
      </Routes>
    </BrowserRouter>
  </React.StrictMode>
)


