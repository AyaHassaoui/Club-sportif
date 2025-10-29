import React from 'react'
import { NIVEAUX } from '../constants/filters'

interface ActiviteFiltersProps {
  filters: {
    niveau: string
    nom: string
    capaciteMin: string
    capaciteMax: string
  }
  onFilterChange: (key: string, value: string) => void
}

export function ActiviteFilters({ filters, onFilterChange }: ActiviteFiltersProps) {
  return (
    <div className="row g-3">
      <div className="col-md-3">
        <label className="form-label">Niveau</label>
        <select 
          className="form-select" 
          value={filters.niveau} 
          onChange={(e) => onFilterChange('niveau', e.target.value)}
        >
          <option value="">Tous les niveaux</option>
          {NIVEAUX.map(niveau => (
            <option key={niveau.value} value={niveau.value}>
              {niveau.label}
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
        <label className="form-label">Capacité min</label>
        <input 
          type="number" 
          className="form-control" 
          placeholder="Min"
          value={filters.capaciteMin}
          onChange={(e) => onFilterChange('capaciteMin', e.target.value)}
        />
      </div>
      
      <div className="col-md-2">
        <label className="form-label">Capacité max</label>
        <input 
          type="number" 
          className="form-control" 
          placeholder="Max"
          value={filters.capaciteMax}
          onChange={(e) => onFilterChange('capaciteMax', e.target.value)}
        />
      </div>
    </div>
  )
}
