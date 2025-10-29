import React from 'react'
import { CATEGORIES } from '../constants/filters'

interface MembreFiltersProps {
  filters: {
    categorie: string
    nom: string
    dateDebut: string
    dateFin: string
  }
  onFilterChange: (key: string, value: string) => void
}

export function MembreFilters({ filters, onFilterChange }: MembreFiltersProps) {
  return (
    <div className="row g-3">
      <div className="col-md-3">
        <label className="form-label">Catégorie</label>
        <select 
          className="form-select" 
          value={filters.categorie} 
          onChange={(e) => onFilterChange('categorie', e.target.value)}
        >
          <option value="">Toutes les catégories</option>
          {CATEGORIES.map(categorie => (
            <option key={categorie.value} value={categorie.value}>
              {categorie.label}
            </option>
          ))}
        </select>
      </div>
      
      <div className="col-md-3">
        <label className="form-label">Nom (recherche)</label>
        <input 
          type="text" 
          className="form-control" 
          placeholder="Rechercher par nom..."
          value={filters.nom}
          onChange={(e) => onFilterChange('nom', e.target.value)}
        />
      </div>
      
      <div className="col-md-2">
        <label className="form-label">Date début</label>
        <input 
          type="date" 
          className="form-control" 
          value={filters.dateDebut}
          onChange={(e) => onFilterChange('dateDebut', e.target.value)}
        />
      </div>
      
      <div className="col-md-2">
        <label className="form-label">Date fin</label>
        <input 
          type="date" 
          className="form-control" 
          value={filters.dateFin}
          onChange={(e) => onFilterChange('dateFin', e.target.value)}
        />
      </div>
    </div>
  )
}
